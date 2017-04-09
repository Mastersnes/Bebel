package factory;

import servlet.inscription.InscriptionServletRequest;
import utils.TokenUtils;
import bdd.UserDAO;
import bean.ComplexUser;

public class UserFactory {
	private static UserFactory instance;

	private UserFactory() {
	}

	public static synchronized UserFactory getInstance() {
		if (instance == null) {
			instance = new UserFactory();
		}
		return instance;
	}

	public ComplexUser create(final InscriptionServletRequest request) {
		final ComplexUser user = new ComplexUser();
		user.setLogin(request.getLogin());
		user.setMail(request.getMail());
		user.setMdp(request.getMdp());
		user.setVerifToken(TokenUtils.getInstance().generateToken(null));
		user.setVerified(false);
		UserDAO.getInstance().saveUser(user);

		return user;
	}
}
