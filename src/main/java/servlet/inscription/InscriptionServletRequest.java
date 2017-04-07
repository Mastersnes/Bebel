package servlet.inscription;

/**
 * Requete d'inscription
 * 
 * @author Mayitabel
 * 
 */
public class InscriptionServletRequest {
    private String login;
    private String mail;
    private String verifMail;
    private String mdp;
    private String verifMdp;

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
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param email
     *            the mail to set
     */
    public void setMail(final String mail) {
        this.mail = mail;
    }

    /**
     * @return the verifMail
     */
    public String getVerifMail() {
        return verifMail;
    }

    /**
     * @param verifMail
     *            the verifMail to set
     */
    public void setVerifMail(final String verifMail) {
        this.verifMail = verifMail;
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

    /**
     * @return the verifMdp
     */
    public String getVerifMdp() {
        return verifMdp;
    }

    /**
     * @param verifMdp
     *            the verifMdp to set
     */
    public void setVerifMdp(final String verifMdp) {
        this.verifMdp = verifMdp;
    }
}
