package fr.univamu.iut.rapidamanger.dish;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe permettant d'accéder aux plats stockés dans la base de données
 */
public class DishRepository implements DishRepositoryInterface, Closeable {

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
     * Méthode permettant de récupérer un plat à partir de son identifiant
     * @param id identifiant du plat recherché
     * @return un objet Dish représentant le plat recherché
     */
    @Override
    public Dish getDish(String id) {
        return null;
    }

    /**
     * Méthode permettant de récupérer la liste de tous les plats
     * @return une liste d'objets Dish
     */
    @Override
    public ArrayList<Dish> getAllDishs() {
        return null;
    }

    /**
     * Méthode permettant de mettre à jour un plat
     * @param id identifiant du plat à mettre à jour
     * @param name nouveau nom du plat
     * @param description nouvelle description du plat
     * @param price nouveau prix du plat
     * @return true si le plat existe et la mise à jour a été faite, false sinon
     */
    @Override
    public boolean updateDish(String id, String name, String description, String price) {
        return false;
    }

    /**
     * Méthode permettant de supprimer un plat
     * @param id identifiant du plat à supprimer
     * @return true si le plat a pu être supprimé, false sinon
     */
    @Override
    public boolean deleteDish(String id){return false;}

    /**
     * Méthode permettant de créer un nouveau plat
     * @param name nom du plat à créer
     * @param description description du plat à créer
     * @param price prix du plat à créer
     * @return l'identifiant du plat créé
     */
    @Override
    public String createDish(String name, String description, String price) {return null;}
}
