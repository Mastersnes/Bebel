package servlet.checkInscription;

import org.apache.commons.lang.StringUtils;

import servlet.abstrait.GeneralException;
import bean.ComplexUser;

/**
 * Validator du servlet d'authentification
 * 
 * @author Mayitabel
 * 
 */
public class CheckInscriptionValidator {
	    /**
     * Verifie que les champs ne sont pas null
     * 
     * @param request
     * @throws GeneralException
     */
    public void checkRequest(final CheckInscriptionServletRequest request) throws GeneralException {
		final String mail = request.getMail();
		final String token = request.getToken();

		if(StringUtils.isEmpty(mail)) {
		    throw new GeneralException(1, "Le mail n'est pas indiqué.");
		}
		if(StringUtils.isEmpty(token)) {
		    throw new GeneralException(1, "La clef de verification n'est pas indiqué.");
		}
	}

	    /**
     * Verifie que le token d'inscription est valide
     * 
     * @param user
     * @param request
     * @throws GeneralException
     */
    public void checkToken(final ComplexUser user, final String requestToken) throws GeneralException {
		final String userToken = user.getVerifToken();
        if (!requestToken.equals(userToken)) {
            throw new GeneralException(4,
                    "La clef d'inscription est invalide. Veuillez verifier le lien dans le mail d'inscription.");
        }
	}
}
