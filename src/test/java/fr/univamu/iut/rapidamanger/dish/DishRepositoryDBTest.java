package fr.univamu.iut.rapidamanger.dish;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DishRepositoryDBTest {

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
    void getDish() {
        Dish result = dishRepo.getDish("1");

        assertEquals(1, result.id);
        assertEquals("Nom du Plat 1", result.name);
        assertEquals("Description du Plat 1", result.description);
        assertEquals(Float.valueOf(1.50F), result.price);
    }

    @Test
    void getAllDishs() {
        ArrayList<Dish> result = dishRepo.getAllDishs();

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).id);
        assertEquals(2, result.get(1).id);
    }

    @Test
    void createDish() {
        dishRepo.createDish("newDish", "newDescription", "5");

        assertEquals(3, dishRepo.getAllDishs().size());
        assertEquals("newDish", dishRepo.getAllDishs().get(dishRepo.getAllDishs().size()-1).name);
    }

    @Test
    void updateDish() {
        Random rand = new Random();
        int randomInt = rand.nextInt(100);
        dishRepo.updateDish("2", "Nom du Plat 2", "Description du Plat 2", String.valueOf(randomInt));

        assertEquals(randomInt, (int) Double.parseDouble(String.valueOf(dishRepo.getDish("2").price)));
    }

    @Test
    void updateDishWithDishIdNonExistent() {
       assertFalse(dishRepo.updateDish("4561", "Nom du Plat 2", "Description du Plat 2", "3"));
    }

    @Test
    void deleteDish() {
        dishRepo.deleteDish(dishRepo.getAllDishs().get(dishRepo.getAllDishs().size()-1).id.toString());

        assertEquals(2, dishRepo.getAllDishs().size());
    }
}