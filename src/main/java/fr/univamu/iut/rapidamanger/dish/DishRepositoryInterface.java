package fr.univamu.iut.rapidamanger.dish;

import java.util.ArrayList;

/**
 * Interface d'accès aux données des plats
 */
public interface DishRepositoryInterface {

    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les repas
     */
    public void close();

    /**
     * Méthode retournant le plat dont l'id est passée en paramètre
     * @param id identifiant du plat recherché
     * @return un objet Dish représentant le plat recherché
     */
    public Dish getDish( String id );

    /**
     * Méthode retournant la liste des plats
     * @return une liste d'objets Dish
     */
    public ArrayList<Dish> getAllDishs() ;

    /**
     * Méthode permettant de mettre à jours un plat enregistré
     * @param id identifiant du plat à mettre à jours
     * @param name nouveau nom du plat
     * @param description nouvelle description du plat
     * @param price nouveau prix du plat
     * @return true si le plat existe et la mise à jours a été faite, false sinon
     */
    public boolean updateDish( String id, String name, String description, String price);
}
