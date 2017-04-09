package utils;

import java.util.logging.Level;

public class BebelMailUtils extends MailUtils {
	private static Logger LOGGER = new Logger(BebelMailUtils.class.getName());
	private static BebelMailUtils instance;

	private BebelMailUtils() {
	}

	public static synchronized BebelMailUtils getInstance() {
		if (instance == null) {
			instance = new BebelMailUtils();
		}
		return instance;
	}

	public void sendVerifMail(final String email, final String token) {
		final String subject = "Mail de confirmation";
		final StringBuilder message = new StringBuilder();
		message.append("Bonjour, vous voici inscrit sur Bebel !<br/>");
		message.append("Vous pouvez desormais acceder a tous nos jeux avec vos identifiant !<br/>");
		message.append("Afin de confirmer votre inscription, veuillez suivre ce lien : <br/>");

		String lien = "http://tomcat-bebel.rhcloud.com/Bebel/?mail=?1&token=?2";
		lien = lien.replace("?1", email);
		lien = lien.replace("?2", token);

		String balise = "<a href='?1'>Confirmer l'inscription !</a>";
		balise = balise.replace("?1", lien);

		message.append(balise).append("<br/><br/>");
		message.append("Ceci est un message automatique, merci de ne pas y repondre.");

		LOGGER.log(Level.INFO, message.toString());

		sendMail(email, subject, message.toString());
	}
}
