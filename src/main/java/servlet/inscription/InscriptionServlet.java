package servlet.inscription;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.ServletException;

import servlet.abstrait.AbstractServlet;
import servlet.abstrait.GeneralException;
import servlet.abstrait.GeneralResponse;
import utils.Constantes;
import utils.Logger;
import utils.MailUtils;
import bean.ComplexUser;
import factory.UserFactory;

/**
 * Controller permettant de s'inscrire
 * 
 * @author Mayitabel
 * 
 */
public class InscriptionServlet extends AbstractServlet<InscriptionServletRequest, GeneralResponse> {
    private static final long serialVersionUID = -4647019705021722992L;
    private final Logger logger = new Logger(InscriptionServlet.class.getName());
    private final InscriptionValidator validator = new InscriptionValidator();

    @Override
    protected GeneralResponse doGet(final InscriptionServletRequest request) throws ServletException, IOException {
        return null;
    }

    @Override
    protected GeneralResponse doPost(final InscriptionServletRequest request) throws ServletException, IOException {
        final GeneralResponse response = new GeneralResponse();
        try {
            // On valide la requete
            validator.checkRequest(request);
            validator.checkMail(request);
            validator.checkNotExist(request);

            // Si tout se passe bien, on continue l'inscription
            inscription(request);
        } catch (final GeneralException e) {
            logger.log(Level.WARNING, e.getMessage());
            response.setCodeRetour(e.getCodeRetour());
            response.setMessage(e.getMessage());
        }

        return response;
    }

    private void inscription(final InscriptionServletRequest request) {
        // On creer l'utilisateur
        final ComplexUser user = UserFactory.getInstance().create(request);

        // Et on lui envoit le mail de validation
        MailUtils.getInstance().sendVerifMail(user.getMail(), user.getVerifToken());
    }

    @Override
    protected InscriptionServletRequest getRequest(final String data) {
        return Constantes.GSON.fromJson(data, InscriptionServletRequest.class);
    }

}
