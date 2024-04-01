package fr.univamu.iut.rapidamanger.dish;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'accèder aux livres stockés dans une base de données Mariadb
 */
public class DishRepositoryDB implements DishRepositoryInterface, Closeable {

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
    public DishRepositoryDB(String infoConnection, String user, String pwd ) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    public DishRepositoryDB(Connection connection) {
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
    public Dish getDish(String id) {

        Dish selectedDish = null;

        String query = "SELECT * FROM Dish WHERE id=?";

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
                String description = result.getString("description");
                String price = result.getString("price");

                // création et initialisation de l'objet Dish
                selectedDish = new Dish(id, name, description, price);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedDish;
    }

    @Override
    public ArrayList<Dish> getAllDishs() {
        ArrayList<Dish> listDishs ;

        String query = "SELECT * FROM Dish";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listDishs = new ArrayList<>();

            // récupération du premier (et seul) tuple résultat
            while ( result.next() )
            {
                String id = result.getString("id");
                String name = result.getString("name");
                String description = result.getString("description");
                String price = result.getString("price");

                // création du plat courant
                Dish currentDish = new Dish(id, name, description, price);

                listDishs.add(currentDish);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listDishs;
    }

    @Override
    public boolean updateDish(String id, String name, String description, String price) {
        String query = "UPDATE Dish SET name=?, description=?, price=?  where id=?";
        int nbRowModified = 0;

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, price);
            ps.setString(4, id);

            // exécution de la requête
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }

    @Override
    public boolean deleteDish(String id) {
        String query = "DELETE FROM Dish where id=?";
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

    // curl --request POST --header "Content-Type: application/json" --data '{"login":"testN", "description":"testD", "price":"4"}' http://localhost:8080/rapidamanger-1.0-SNAPSHOT/api/dish
    @Override
    public String createDish(String name, String description, String price) {
        String query = "INSERT INTO `Dish`(`name`, `description`, `price`) VALUES (?,?,?)";
        int newId = -1;
        int nbRowModified = 0;

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) ){
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, price);

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

        return String.valueOf(newId);
    }
}