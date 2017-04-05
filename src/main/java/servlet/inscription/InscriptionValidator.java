package servlet.inscription;

import org.apache.commons.lang.StringUtils;

import servlet.abstrait.GeneralException;
import bdd.UserDAO;
import bean.ComplexUser;

/**
 * Validator du servlet d'authentification
 * 
 * @author Mayitabel
 * 
 */
public class InscriptionValidator {

    /**
     * Verifie que les champs ne sont pas null
     * 
     * @param request
     * @throws GeneralException
     */
    public void checkRequest(final InscriptionServletRequest request) throws GeneralException {
		final String login = request.getLogin();
		final String mail = request.getMail();
		final String mdp = request.getMdp();

        if (StringUtils.isEmpty(login)) {
            throw new GeneralException(1, "Veuillez remplir le champ login.");
        }
        if (StringUtils.isEmpty(mail)) {
            throw new GeneralException(1, "Veuillez remplir le champ email.");
        }
        if (StringUtils.isEmpty(mdp)) {
            throw new GeneralException(1, "Veuillez remplir le champ mot de passe.");
        }
	}

    /**
     * permet de verifier un mail
     * 
     * @param request
     * @throws GeneralException
     */
    public void checkMail(final InscriptionServletRequest request) throws GeneralException {
		final String email = request.getMail();
        if (!email.matches("^[a-zA-Z0-9+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            throw new GeneralException(2, "Votre email n'est pas correct.");
        }
	}

    /**
     * A remettre en JS
     * 
     * @param mdp
     * @throws GeneralException
     */
    public void checkPassword(final InscriptionServletRequest request) throws GeneralException {
        final String mdp = request.getMdp();
        // Contient une majuscule
        if (!mdp.matches("^(?=.*[A-Z]).*$")) {
            throw new GeneralException(3, "Votre mot de passe doit contenir au moins une majuscule.");
        }

		// Contient une minuscule
        if (!mdp.matches("^(?=.*[a-z]).*$")) {
            throw new GeneralException(3, "Votre mot de passe doit contenir au moins une minuscule.");
        }

		// Contient un chiffre
        if (!mdp.matches("^(?=.*\\d).*$")) {
            throw new GeneralException(3, "Votre mot de passe doit contenir au moins un chiffre.");
        }

		// Fait minimum 10 caracteres
        if (mdp.length() < 10) {
            throw new GeneralException(3, "Votre mot de passe doit faire au minimum 10 caracteres.");
        }
	}

    /**
     * Verifie que l'utilisateur n'existe pas deja en base
     * 
     * @param login
     * @param mail
     * @throws GeneralException
     */
    public void checkNotExist(final InscriptionServletRequest request) throws GeneralException {
        final String login = request.getLogin();
        final String mail = request.getMail();
        
		ComplexUser user = UserDAO.getInstance().getUser(login);
		if (user == null) {
			user = UserDAO.getInstance().getUser(mail);
		}

        if (user != null) {
            throw new GeneralException(3, "Vous etes deja inscrit, merci de vous connecter.");
        }
	}
}
