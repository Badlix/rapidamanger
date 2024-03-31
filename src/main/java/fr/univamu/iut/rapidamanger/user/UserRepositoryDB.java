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

    /**
     * Constructeur de la classe
     * @param infoConnection chaîne de caractères avec les informations de connexion
     *                       (p.ex. jdbc:mariadb://mysql-[compte].alwaysdata.net/[compte]_library_db
     * @param user chaîne de caractères contenant l'identifiant de connexion à la base de données
     * @param pwd chaîne de caractères contenant le mot de passe à utiliser
     */
    public UserRepositoryDB(String infoConnection, String user, String pwd ) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    public UserRepositoryDB(Connection connection) {
        this.dbConnection = connection;
    }

    public void setDbConnection(Connection dbConnection) {
        this.dbConnection = dbConnection;
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
                String name = result.getString("name");
                String address = result.getString("address");

                // création et initialisation de l'objet User
                selectedUser = new User(id, name, address);
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
                String name = result.getString("name");
                String address = result.getString("address");

                // création du plat courant
                User currentUser = new User(id, name, address);

                listUsers.add(currentUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUsers;
    }

    @Override
    public boolean updateUser(String id, String name, String password, String address) {
        String query = "";

        if (password != null) {
            query = "UPDATE User SET name=?, password=?, address=? where id=?";
        } else {
            query = "UPDATE User SET name=?, address=? where id=?";
        }

        int nbRowModified = 0;

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, name);

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

    // curl --request POST --header "Content-Type: application/json" --data '{"name":"testN", "password":"mdp", "address":"13090 Aix-en-Provence"}' http://localhost:8080/rapidamanger-1.0-SNAPSHOT/api/user
    @Override
    public String createUser(String name, String password, String address) {
        String query = "INSERT INTO `User`(`name`, `password`, `address`) VALUES (?,?,?)";
        int newId = -1;
        int nbRowModified = 0;

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) ){
            ps.setString(1, name);
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
}