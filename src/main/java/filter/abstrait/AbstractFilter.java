//package filter.abstrait;
//
//
//import response.GeneralResponse;
//import servlet.abstrait.AbstractServlet;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Filter abstrait
// *
// */
//public abstract class AbstractFilter<REQ> extends AbstractServlet<REQ, GeneralResponse> implements Filter {
//
//	/**
//	 * filtre avec un formalisme epure
//	 *
//	 * @param request
//	 *            requete
//	 * @return reponse
//	 */
//	protected abstract GeneralResponse doFilter(final REQ request, final FilterChain chain) throws ServletException,
//			IOException;
//
//	@Override
//	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
//			throws IOException, ServletException {
//		final HttpServletRequest req = (HttpServletRequest) request;
//		final REQ requete = mapRequest(req);
//		final HttpServletResponse res = (HttpServletResponse) response;
//		final GeneralResponse jsonResponse = doFilter(requete, chain);
//
//		if (jsonResponse.getCodeRetour() == 0) {
//			chain.doFilter(request, response);
//		} else {
//			setResponse(res, jsonResponse);
//		}
//	}
//
//	@Override
//	protected GeneralResponse doGet(final REQ request) throws ServletException, IOException {
//		return null;
//	}
//
//	@Override
//	protected GeneralResponse doPost(final REQ request) throws ServletException, IOException {
//		return null;
//	}
//}
