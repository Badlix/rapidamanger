package fr.univamu.iut.rapidamanger.user;

import fr.univamu.iut.rapidamanger.user.User;
import fr.univamu.iut.rapidamanger.user.UserRepositoryDB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryDBTest {
    static UserRepositoryDB userRepo;

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
        userRepo = new UserRepositoryDB(dbConnection);
    }

    @Test
    void getUser() {
        User result = userRepo.getUser("1");

        assertEquals(1, result.id);
        assertEquals("Nom de l'Utilisateur 1", result.login);
        assertEquals("Adresse de l'Utilisateur 1", result.address);
    }

    @Test
    void getAllUsers() {
        ArrayList<User> result = userRepo.getAllUsers();

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).id);
        assertEquals(2, result.get(1).id);
    }

    @Test
    void createUser() {
        userRepo.createUser("newUser", "newPassword", "newAddress");

        assertEquals(3, userRepo.getAllUsers().size());
        assertEquals("newUser", userRepo.getAllUsers().get(userRepo.getAllUsers().size()-1).login);
    }

    @Test
    void updateUser() {
        Random rand = new Random();
        int randomInt = rand.nextInt(100);
        userRepo.updateUser("2", "Nom de l'Utilisateur 2", "Mdp2", String.valueOf(randomInt));

        assertEquals(randomInt, Integer.valueOf(userRepo.getUser("2").address));
    }

    @Test
    void updateUserWithUserIdNonExistent() {
        assertFalse(userRepo.updateUser("4561", "Nom de l'Utilisateur 3", "Mdp3", "3"));
    }

    @Test
    void deleteUser() {
        userRepo.deleteUser(userRepo.getAllUsers().get(userRepo.getAllUsers().size()-1).id.toString());

        assertEquals(2, userRepo.getAllUsers().size());
    }
}