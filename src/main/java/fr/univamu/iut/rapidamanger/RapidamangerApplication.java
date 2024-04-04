package fr.univamu.iut.rapidamanger;

import fr.univamu.iut.rapidamanger.dish.DishRepositoryDB;
import fr.univamu.iut.rapidamanger.dish.DishResource;
import fr.univamu.iut.rapidamanger.dish.DishService;
import fr.univamu.iut.rapidamanger.user.UserRepositoryDB;
import fr.univamu.iut.rapidamanger.user.UserResource;
import fr.univamu.iut.rapidamanger.user.UserService;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RapidamangerApplication extends Application {

    @Override
    public Set<Object> getSingletons() {
        Set<Object> set = new HashSet<>();

        // Création de la connection à la base de données et initialisation du service associé
        DishService dishService = null;
        UserService userService = null;
        String bd_info = "jdbc:mariadb://mysql-cc2-archi.alwaysdata.net/cc2-archi_plat";
        String bd_user = "cc2-archi";
        String mdp = "mdpAMU12345";
        Connection dbConnection = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            dbConnection = DriverManager.getConnection( bd_info, bd_user, mdp );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        DishRepositoryDB dbDish = new DishRepositoryDB(dbConnection);
        dishService = new DishService(dbDish);
        UserRepositoryDB dbUser = new UserRepositoryDB(dbConnection);
        userService = new UserService(dbUser);

        set.add(new DishResource(dishService));
        set.add(new UserResource(userService));

        return set;
    }
}