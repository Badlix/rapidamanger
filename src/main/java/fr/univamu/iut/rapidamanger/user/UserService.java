package fr.univamu.iut.rapidamanger.user;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les plats
     */
    protected UserRepositoryInterface userRepo;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param userRepo objet implémentant l'interface d'accès aux données
     */
    public UserService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Méthode retournant les informations sur les plats au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllUsersJSON(){

        ArrayList<User> allUsers = userRepo.getAllUsers();

        // création du json et conversion de la liste de plats
        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allUsers);
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
    public String getUserJSON(String id ){
        String result = null;
        User myUser = userRepo.getUser(id);

        // si le plat a été trouvé
        if( myUser != null ) {

            // création du json et conversion du plat
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myUser);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Méthode permettant de mettre à jours les informations d'un utilisateur
     * @param id id de l'utilisateur à mettre à jours
     * @param name les nouvelles informations a été utiliser
     * @param password les nouvelles informations a été utiliser
     * @param address les nouvelles informations a été utiliser
     * @return true si l'utilisateur a pu être mis à jours
     */
    public String  updateUser(String id, String name, String password, String address) {

        String result = null;

        // si le plat a été trouvé
        if( userRepo.updateUser(id, name, password, address) ) {
            JSONObject updatedUser = new JSONObject();
            updatedUser.put("id", id);
            result = updatedUser.toString();
        }

        return result;
    }

    /**
     * Méthode permettant de supprimer un plat
     * @param id id du plat à supprimer
     * @return true si le plat a pu être supprimé
     */
    public String deleteUser(String id) {
        JSONObject result = new JSONObject();

        // si l'utilisateur a été trouvé
        if( userRepo.deleteUser(id) ) {
            result.put("id", id);
        }
        return result.toString();
    }

    /**
     * Méthode permettant de créer un utilisateur
     * @param name nom de l'utilisateur à creer
     * @param password mot de passe de l'utilisateur à creer
     * @param address adresse de l'utilisateur à creer
     * @return true si le plat a pu être supprimé
     */
    public String createUser(String name, String password, String address) {
        JSONObject result = new JSONObject();
        String idOfNewUser = userRepo.createUser(name, password, address);
        result.put("id", idOfNewUser);
        return result.toString();
    }
}
