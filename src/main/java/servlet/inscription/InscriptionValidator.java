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
			throw new GeneralException(1, "Veuillez remplir le champ Identifiant.");
		}
		if (StringUtils.isEmpty(mail)) {
			throw new GeneralException(1, "Veuillez remplir le champ Email.");
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
		final String verifMail = request.getVerifMail();
		if (!StringUtils.equals(email, verifMail)) {
			throw new GeneralException(2, "Veuillez noter le meme email dans le champ de confirmation.");
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
		final String verifMdp = request.getVerifMdp();
		if (!StringUtils.equals(mdp, verifMdp)) {
			throw new GeneralException(2, "Veuillez noter le meme mot de passe dans le champ de confirmation.");
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
			if (user.isVerified()) {
				throw new GeneralException(3, "Vous etes deja inscrit, merci de vous connecter.");
			} else {
				throw new GeneralException(3,
						"Vous etes deja inscrit, merci de confirmer votre inscription en cliquant sur le lien dans l'email.");
			}
		}
	}
}
