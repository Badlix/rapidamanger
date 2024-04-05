package fr.univamu.iut.rapidamanger.dish;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DishServiceTest {

    static DishRepositoryDB dishRepo;

    @BeforeAll
    public static void init() {
        String bd_info = "jdbc:mariadb://mysql-cc2-archi.alwaysdata.net/cc2-archi_test";
        String bd_user = "cc2-archi";
        String mdp = "mdpAMU12345";
        Connection dbConnection = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            dbConnection = DriverManager.getConnection( bd_info, bd_user, mdp );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        dishRepo = new DishRepositoryDB(dbConnection);
    }


    @Test
    void getAllDishsJSON() {
        JSONObject dish1 = new JSONObject();
        dish1.put("name", "Nom du Plat 1");
        dish1.put("id", 1);
        dish1.put("description", "Description du Plat 1");
        dish1.put("price", 1.50);
        JSONObject dish2 = new JSONObject();
        dish2.put("name", "Nom du Plat 2");
        dish2.put("id", 2);
        dish2.put("description", "Description du Plat 2");
        dish2.put("price", 1.50);

    }

    @Test
    void getDishJSON() {
    }

    @Test
    void updateDish() {
    }

    @Test
    void deleteDish() {
    }

    @Test
    void deleteMenuWithDeletedDish() {
    }

    @Test
    void createDish() {
    }
}