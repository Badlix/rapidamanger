package fr.univamu.iut.rapidamanger.user;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'accèder aux livres stockés dans une base de données Mariadb
 */
public class UserRepositoryDB implements UserRepositoryInterface, Closeable {

    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection ;

    public UserRepositoryDB(Connection connection) {
        this.dbConnection = connection;
    }

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

        User selectedUser = null;

        String query = "SELECT * FROM User WHERE id=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, id);

            // exécution de la requête
            ResultSet result = ps.executeQuery();

            // récupération du premier (et seul) tuple résultat
            // (si la référence du plat est valide)
            if( result.next() )
            {
                String login = result.getString("login");
                String address = result.getString("address");

                // création et initialisation de l'objet User
                selectedUser = new User(id, login, address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedUser;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> listUsers;

        String query = "SELECT * FROM User";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listUsers = new ArrayList<>();

            // récupération du premier (et seul) tuple résultat
            while ( result.next() )
            {
                String id = result.getString("id");
                String login = result.getString("login");
                String address = result.getString("address");

                // création du plat courant
                User currentUser = new User(id, login, address);

                listUsers.add(currentUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUsers;
    }

    @Override
    public boolean updateUser(String id, String login, String password, String address) {
        String query;

        if (password != null) {
            query = "UPDATE User SET login=?, password=?, address=? where id=?";
        } else {
            query = "UPDATE User SET login=?, address=? where id=?";
        }

        int nbRowModified = 0;

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, login);

            if (password != null) {
                ps.setString(2, password);
                ps.setString(3, address);
                ps.setString(4, id);
            } else {
                ps.setString(2, address);
                ps.setString(3, id);
            }

            // exécution de la requête
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }

    @Override
    public boolean deleteUser(String id) {
        String query = "DELETE FROM User where id=?";
        int nbRowModified = 0;

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, id);

            // exécution de la requête
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }

    // curl --request POST --header "Content-Type: application/json" --data '{"login":"testN", "password":"mdp", "address":"13090 Aix-en-Provence"}' http://localhost:8080/rapidamanger-1.0-SNAPSHOT/api/user
    @Override
    public String createUser(String login, String password, String address) {
        String query = "INSERT INTO `User`(`login`, `password`, `address`) VALUES (?,?,?)";
        int newId = -1;
        int nbRowModified = 0;

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) ){
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, address);

            // exécution de la requête
            nbRowModified = ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    newId = rs.getInt(1); // Retrieve the generated ID
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (nbRowModified != 0) {
            return String.valueOf(newId);
        }

        return null;
    }

    @Override
    public String authentificate(String login, String password) {
        String query = "SELECT id FROM `User` where login=? and password=?";
        int userId = -1;

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) ){
            ps.setString(1, login);
            ps.setString(2, password);

            // exécution de la requête
            ResultSet rs = ps.executeQuery();

            // vérification si le résultat contient des données
            if (rs.next()) {
                userId = rs.getInt("id"); // récupération de la colonne "id" du résultat
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (userId == -1) {
            return String.valueOf(false);
        }

        return String.valueOf(userId);
    }
}