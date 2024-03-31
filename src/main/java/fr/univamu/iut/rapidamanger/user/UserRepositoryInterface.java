package fr.univamu.iut.rapidamanger.user;

import java.util.ArrayList;

/**
 * Interface d'accès aux données des plats
 */
public interface UserRepositoryInterface {

    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les repas
     */
    public void close();

    /**
     * Méthode retournant le plat dont l'id est passée en paramètre
     * @param id identifiant de l'utilisateur recherché
     * @return un objet User représentant l'utilisateur recherché
     */
    public User getUser(String id );

    /**
     * Méthode retournant la liste des utilisateurs
     * @return une liste d'objets User
     */
    public ArrayList<User> getAllUsers() ;

    /**
     * Méthode permettant de mettre à jours un utilisateur enregistré
     * @param id identifiant de l'utilisateur à mettre à jours
     * @param name nouveau nom de l'utilisateur
     * @param address nouvelle adresse de l'utilisateur
     * @return true si le plat existe et la mise à jours a été faite, false sinon
     */
    public boolean updateUser( String id, String name, String password, String address);

    /**
     * Méthode permettant de supprimer un utilisateur
     * @param id id de l'utilisateur à supprimer
     * @return true si l'utilisateur a pu être supprimé
     */
    public boolean deleteUser( String id );

    /**
     * Méthode permettant de creer un utilisateur
     * @param name nom de l'utilisateur à creer
     * @param password mot de passe de l'utilisateur à creer
     * @param address adresse de l'utilisateur à creer
     * @return l'id de l'utilisateur crée
     */
    public String createUser( String name, String password, String address );
}
