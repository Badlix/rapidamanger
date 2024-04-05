package fr.univamu.iut.rapidamanger.user;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe permettant d'accéder aux utilisateurs stockés dans la base de données
 */
public class UserRepository implements UserRepositoryInterface, Closeable {

    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection ;

    /**
     * Méthode permettant de fermer la connexion à la base de données
     */
    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Méthode permettant de récupérer un utilisateur à partir de son identifiant
     * @param id identifiant de l'utilisateur recherché
     * @return un objet User représentant l'utilisateur recherché
     */
    @Override
    public User getUser(String id) {
        return null;
    }

    /**
     * Méthode permettant de récupérer la liste de tous les utilisateurs
     * @return une liste d'objets User
     */
    @Override
    public ArrayList<User> getAllUsers() {
        return null;
    }

    /**
     * Méthode permettant de mettre à jour un utilisateur
     * @param id identifiant de l'utilisateur à mettre à jour
     * @param login nouveau login de l'utilisateur
     * @param password nouveau mot de passe de l'utilisateur
     * @param address nouvelle adresse de l'utilisateur
     * @return true si l'utilisateur existe et la mise à jour a été faite, false sinon
     */
    @Override
    public boolean updateUser(String id, String login, String password, String address) {
        return false;
    }

    /**
     * Méthode permettant de supprimer un utilisateur
     * @param id identifiant de l'utilisateur à supprimer
     * @return true si l'utilisateur a pu être supprimé, false sinon
     */
    @Override
    public boolean deleteUser(String id){return false;}

    /**
     * Méthode permettant de créer un nouvel utilisateur
     * @param login login de l'utilisateur à créer
     * @param password mot de passe de l'utilisateur à créer
     * @param address adresse de l'utilisateur à créer
     * @return l'identifiant de l'utilisateur créé
     */
    @Override
    public String createUser(String login, String password, String address) {return null;}

    /**
     * Méthode permettant d'authentifier un utilisateur
     * @param login login de l'utilisateur
     * @param password mot de passe de l'utilisateur
     * @return l'identifiant de l'utilisateur si l'authentification est réussie, null sinon
     */
    @Override
    public String authentificate(String login, String password) {return null;}
}

