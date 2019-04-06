package servlet.abstrait;

import com.google.gson.JsonSyntaxException;
import util.Constantes;
import util.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Servlet abstraite
 */
public abstract class AbstractServlet<REQ, RESP> extends HttpServlet {
	protected Logger logger = new Logger(getClass());

	protected HttpServletRequest httpRequest;

	protected abstract RESP doGet(final REQ request) throws ServletException, IOException;
	protected abstract RESP doPost(final REQ request) throws ServletException, IOException;
	protected abstract Class<REQ> getRequestType();

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
			IOException {
		httpRequest = req;
		setResponse(resp, doGet(mapRequest(req)));
	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
			IOException {
		httpRequest = req;
		setResponse(resp, doPost(mapRequest(req)));
	}

	/**
	 * Transforme la requete http en requete json
	 * 
	 */
    protected REQ mapRequest(final HttpServletRequest req) {
		final String data = getAttribute(req);
		try {
			return Constantes.GSON.fromJson(data, getRequestType());
		} catch (final JsonSyntaxException e) {
			logger.err("Requete invalide", e);
		}
		return null;
	}

	/**
	 * Recupere le flux json present dans la requete
	 * 
	 */
	private String getAttribute(final HttpServletRequest req) {
		StringBuilder data = new StringBuilder();
		try (final BufferedReader in = new BufferedReader(new InputStreamReader(req.getInputStream()))) {
			String line;
			while ((line = in.readLine()) != null) data.append(line);
		} catch (final IOException e) {
			logger.err("Erreur d'ouverture de la requete", e);
		}
		return data.toString();
	}

	/**
	 * Ecrit la reponse au format json
	 * 
	 * @param resp
	 * @param response
	 */
	protected void setResponse(final HttpServletResponse resp, final RESP response) {
		try (final PrintWriter writer = resp.getWriter()) {
			writer.append(Constantes.GSON.toJson(response));
			writer.flush();
		} catch (final IOException e) {
			logger.err("Reponse invalide", e);
		}
	}

	protected String getClientIpAddr() {
		String ip = httpRequest.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = httpRequest.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = httpRequest.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = httpRequest.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = httpRequest.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = httpRequest.getRemoteAddr();
		}
		return ip;
	}
}
