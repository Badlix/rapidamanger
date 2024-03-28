package fr.univamu.iut.rapidamanger.dish;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe permettant d'accèder aux plats stockés dans la base de données
 */
public class DishRepository implements DishRepositoryInterface, Closeable {

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
    public Dish getDish(String id) {
        return null;
    }

    @Override
    public ArrayList<Dish> getAllDishs() {
        return null;
    }

    @Override
    public boolean updateDish(String id, String name, String description, String price) {
        return false;
    }
}
