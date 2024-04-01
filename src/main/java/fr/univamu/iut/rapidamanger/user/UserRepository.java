package fr.univamu.iut.rapidamanger.user;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe permettant d'accèder aux plats stockés dans la base de données
 */
public class UserRepository implements UserRepositoryInterface, Closeable {

    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection ;
    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public User getUser(String id) {
        return null;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return null;
    }

    @Override
    public boolean updateUser(String id, String login, String password, String address) {
        return false;
    }

    @Override
    public boolean deleteUser(String id){return false;}

    @Override
    public String createUser(String login, String password, String address) {return null;}

    @Override
    public String authentificate(String login, String password) {return null;}
}
