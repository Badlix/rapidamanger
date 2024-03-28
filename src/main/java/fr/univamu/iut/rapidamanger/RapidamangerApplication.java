package fr.univamu.iut.rapidamanger;

import fr.univamu.iut.rapidamanger.dish.DishRepositoryDB;
import fr.univamu.iut.rapidamanger.dish.DishResource;
import fr.univamu.iut.rapidamanger.dish.DishService;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RapidamangerApplication extends Application {

    @Override
    public Set<Object> getSingletons() {
        Set<Object> set = new HashSet<>();

        // Création de la connection à la base de données et initialisation du service associé
        DishService service = null ;
        String bd_info = "jdbc:mariadb://mysql-cc2-archi.alwaysdata.net/cc2-archi_plat";
        String bd_user = "cc2-archi";
        String mdp = "mdpAMU12345";
        try{
            DishRepositoryDB db = new DishRepositoryDB(bd_info, bd_user, mdp);
            service = new DishService(db);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }

        // Création de la ressource en lui passant paramètre les services à exécuter en fonction
        // des différents endpoints proposés (i.e. requêtes HTTP acceptées)
        set.add(new DishResource(service));

        return set;
    }
}