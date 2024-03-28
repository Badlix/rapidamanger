package fr.univamu.iut.rapidamanger.dish;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

public class DishService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les plats
     */
    protected DishRepositoryInterface dishRepo;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param dishRepo objet implémentant l'interface d'accès aux données
     */
    public  DishService( DishRepositoryInterface dishRepo) {
        this.dishRepo = dishRepo;
    }

    /**
     * Méthode retournant les informations sur les plats au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllDishsJSON(){

        ArrayList<Dish> allDishs = dishRepo.getAllDishs();

        // création du json et conversion de la liste de plats
        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allDishs);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }

    /**
     * Méthode retournant au format JSON les informations sur un plat recherché
     * @param id l'id du plat recherché
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getDishJSON( String id ){
        String result = null;
        Dish myDish = dishRepo.getDish(id);

        // si le plat a été trouvé
        if( myDish != null ) {

            // création du json et conversion du plat
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myDish);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Méthode permettant de mettre à jours les informations d'un plat
     * @param id id du plat à mettre à jours
     * @param dish les nouvelles infromations a été utiliser
     * @return true si le plat a pu être mis à jours
     */
    public boolean updateDish(String id, Dish dish) {
        return dishRepo.updateDish(id, dish.name, dish.description, dish.price);
    }
}
