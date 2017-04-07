package servlet.checkInscription;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.ServletException;

import servlet.abstrait.AbstractServlet;
import servlet.abstrait.GeneralException;
import servlet.abstrait.GeneralResponse;
import utils.CommunConstantes;
import utils.Logger;
import bdd.UserDAO;
import bean.ComplexUser;

/**
 * Controller permettant de valider le mail
 * 
 * @author Mayitabel
 * 
 */
public class CheckInscriptionServlet extends AbstractServlet<CheckInscriptionServletRequest, GeneralResponse> {
	private static final long serialVersionUID = -4647019705021722992L;
	private final Logger logger = new Logger(CheckInscriptionServlet.class.getName());
	private final CheckInscriptionValidator validator = new CheckInscriptionValidator();

	@Override
	protected GeneralResponse doGet(final CheckInscriptionServletRequest request) throws ServletException, IOException {
        final GeneralResponse response = new GeneralResponse();
        try {
            validator.checkRequest(request);

            final ComplexUser user = UserDAO.getInstance().getUser(request.getMail());
            if (user == null) {
                throw new GeneralException(2, "Cet email est inconnu, merci de vous inscrire en premier lieu.");
            }

            if (user.isVerified()) {
                throw new GeneralException(3, "Votre email est deja valide, vous pouvez vous connecter.");
            }

            validator.checkToken(user, request.getToken());

            user.setVerified(true);
            response.setMessage("Felicitation, vous pouvez maintenant vous connecter !");
        } catch (final GeneralException e) {
            logger.log(Level.WARNING, e.getMessage());
            response.setCodeRetour(e.getCodeRetour());
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    protected GeneralResponse doPost(final CheckInscriptionServletRequest request) throws ServletException, IOException {
        new GeneralResponse();
        return null;
	}

	@Override
	protected CheckInscriptionServletRequest getRequest(final String data) {
		return CommunConstantes.GSON.fromJson(data, CheckInscriptionServletRequest.class);
	}

}
