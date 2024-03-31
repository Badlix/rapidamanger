package fr.univamu.iut.rapidamanger.user;

/**
 * Classe représentant un utilisateur
 */
public class User {

    /**
     * Id de l'utilisateur
     */
    protected String id;

    /**
     * nom de l'utilisateur
     */
    protected String name;

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
     * @param name nom de l'utilisateur
     * @param address adresse de l'utilisateur
     */
    public User(String id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    /**
     * Méthode permettant d'accéder à l'id de l'utilisateur
     * @return un chaîne de caractères avec lid de l'utilisateur
     */
    public String getId() {
        return id;
    }

    /**
     * Méthode permettant d'accéder au nom de l'utilisateur
     * @return un chaîne de caractères avec le nom de l'utilisateur
     */
    public String getName() {
        return name;
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
        this.id = id;
    }

    /**
     * Méthode permettant de modifier le nom de l'utilisateur
     * @param name une chaîne de caractères avec le nom à utiliser
     */
    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}