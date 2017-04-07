package servlet.connexionBebel;

/**
 * Requete de connexion
 * 
 * @author Mayitabel
 * 
 */
public class ConnexionBebelServletRequest {
    private String login;
    private String mdp;

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login
     *            the login to set
     */
    public void setLogin(final String login) {
        this.login = login;
    }

    /**
     * @return the mdp
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * @param mdp
     *            the mdp to set
     */
    public void setMdp(final String mdp) {
        this.mdp = mdp;
    }
}
