package fr.univamu.iut.rapidamanger.user;

/**
 * Classe représentant un utilisateur
 */
public class User {

    /**
     * Id de l'utilisateur
     */
    protected Integer id;

    /**
     * login de l'utilisateur
     */
    protected String login;

    /**
     * Adresse de l'utilisateur
     */
    protected String address;

    /**
     * Constructeur par défaut
     */
    public User(){
    }

    /**
     * Constructeur pour les requêtes GET, le mot de passe n'est pas initialisé pour des raisons de sécurité
     * @param id id de l'utilisateur
     * @param login nom de l'utilisateur
     * @param address adresse de l'utilisateur
     */
    public User(String id, String login, String address){
        this.id = Integer.parseInt(id);
        this.login = login;
        this.address = address;
    }

    /**
     * Méthode permettant d'accéder à l'id de l'utilisateur
     * @return un chaîne de caractères avec lid de l'utilisateur
     */
    public Integer getId() {
        return id;
    }

    /**
     * Méthode permettant d'accéder au nom de l'utilisateur
     * @return un chaîne de caractères avec le nom de l'utilisateur
     */
    public String getLogin() {
        return login;
    }

    /**
     * Méthode permettant d'accéder à l'adresse de l'utilisateur
     * @return un chaîne de caractères avec l'adresse de l'utilisateur
     */
    public String getAddress() {
        return address;
    }

    /**
     * Méthode permettant de modifier l'id de l'utilisateur
     * @param id une chaîne de caractères avec l'id à utiliser
     */
    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    /**
     * Méthode permettant de modifier le nom de l'utilisateur
     * @param login une chaîne de caractères avec le nom à utiliser
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Méthode permettant de modifier l'adresse de l'utilisateur
     * @param address une chaîne de caractères avec l'adresse de l'utilisateur
     */
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}