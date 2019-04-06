package servlet.youlose;

import request.SaveRequest;
import servlet.abstrait.AbstractServlet;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Controller permettant d'effectuer une sauvegarde youlose
 * 
 */
public class SaveServlet extends AbstractServlet<SaveRequest, String> {

	@Override
	protected String doGet(final SaveRequest request) throws ServletException, IOException {
		return null;
	}

	@Override
	protected String doPost(final SaveRequest request) throws ServletException, IOException {
		return null;
	}

	@Override
	protected Class<SaveRequest> getRequestType() {
		return SaveRequest.class;
	}
}
