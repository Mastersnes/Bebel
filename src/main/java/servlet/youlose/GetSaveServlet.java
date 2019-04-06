package servlet.youlose;

import request.GetSaveRequest;
import response.GetSaveResponse;
import servlet.abstrait.AbstractServlet;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Controller permettant de recuperer une sauvegarde YouLose
 * 
 */
public class GetSaveServlet extends AbstractServlet<GetSaveRequest, GetSaveResponse> {

	@Override
	protected GetSaveResponse doGet(final GetSaveRequest request) throws ServletException, IOException {
		final GetSaveResponse response = new GetSaveResponse();

		return response;
	}

	@Override
	protected GetSaveResponse doPost(final GetSaveRequest request) throws ServletException, IOException {
		return null;
	}

	@Override
	protected Class<GetSaveRequest> getRequestType() {
		return GetSaveRequest.class;
	}
}
