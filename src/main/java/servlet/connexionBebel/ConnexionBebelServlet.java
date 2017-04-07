package servlet.connexionBebel;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.ServletException;

import servlet.abstrait.AbstractServlet;
import servlet.abstrait.GeneralException;
import utils.CommunConstantes;
import utils.Logger;
import utils.TokenUtils;
import bdd.UserDAO;
import bean.ComplexUser;

/**
 * Controller permettant de se connecter
 * 
 * @author Mayitabel
 * 
 */
public class ConnexionBebelServlet extends AbstractServlet<ConnexionBebelServletRequest, ConnexionBebelServletResponse> {
    private static final long serialVersionUID = -4647019705021722992L;
    private final Logger logger = new Logger(ConnexionBebelServlet.class.getName());
    private final ConnexionBebelValidator validator = new ConnexionBebelValidator();

    @Override
    protected ConnexionBebelServletResponse doGet(final ConnexionBebelServletRequest request) throws ServletException,
            IOException {
        return null;
    }

    @Override
    protected ConnexionBebelServletResponse doPost(final ConnexionBebelServletRequest request) throws ServletException,
            IOException {
        final ConnexionBebelServletResponse response = new ConnexionBebelServletResponse();
        try {
            validator.checkRequest(request);

            final ComplexUser user = UserDAO.getInstance().getUser(request.getLogin());
            if (user == null) {
                throw new GeneralException(2, "Utilisateur inconnu");
            }

            validator.checkPassword(user, request.getMdp());

            if (!user.isVerified()) {
                throw new GeneralException(4, "Veuillez valider votre inscription en verifiant vos mail.");
            }

            // Token de connexion
            response.setToken(TokenUtils.getInstance().generateToken(null));
        } catch (final GeneralException e) {
            logger.log(Level.WARNING, e.getMessage());
            response.setCodeRetour(e.getCodeRetour());
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    protected ConnexionBebelServletRequest getRequest(final String data) {
        return CommunConstantes.GSON.fromJson(data, ConnexionBebelServletRequest.class);
    }

}
