package fr.univamu.iut.rapidamanger.dish;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.checkerframework.checker.units.qual.A;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
     * @param name le nouveau nom
     * @param description la nouvelle description
     * @param price le nouveau prix
     * @return true si le plat a été mis à jour
     */
    public String updateDish(String id, String name, String description, String price) {
        String result = null;

        // si le plat a été trouvé
        if( dishRepo.updateDish(id, name, description, price) ) {
            JSONObject updatedDish = new JSONObject();
            updatedDish.put("id", Integer.parseInt(id));
            result = updatedDish.toString();
        }

        return result;
    }

    /**
     * Méthode permettant de supprimer un plat
     * @param id id du plat à supprimer
     * @return true si le plat a pu être supprimé
     */
    public String deleteDish(String id) {
        String result = null;

        // si le plat a été trouvé
        if( dishRepo.deleteDish(id) ) {
            JSONObject deletedDish = new JSONObject();
            deletedDish.put("id", Integer.parseInt(id));
            result = deletedDish.toString();

            /*System.out.println(deletedDish);
            deleteMenuWithDeletedDish(deletedDish);
            result = deleteMenuWithDeletedDish(deletedDish);*/
        }
        return result;
    }

    public String deleteMenuWithDeletedDish(JSONObject deletedDish) throws IOException {

        // GET ALL MENU ID

        String jsonResponse = "";
        try {
            String uri = "http://51.15.238.170:8080/rapidamanger-menu-1.0-SNAPSHOT/api/menu";
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                jsonResponse = response.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray listMenu = new JSONArray(jsonResponse);
        ArrayList<Integer> listMenuId = new ArrayList<>();

        for (int i = 0; i < listMenu.length(); i++) {
            listMenuId.add(listMenu.getJSONObject(i).getInt("id_menu"));
        }

        // DELETE LE PLAT POUR LES MENUS QU'IL LE POSSÈDE

        for (int id : listMenuId) {
            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("dish_id", String.valueOf(deletedDish.getInt("id")));
            String requestBody = jsonRequest.toString();

            String uri = "http://51.15.238.170:8080/rapidamanger-menu-1.0-SNAPSHOT/api/menu/" + id + "/remove";
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Envoi des données JSON
            OutputStream os = connection.getOutputStream();
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        return listMenuId.toString();
    }

    /**
     * Méthode permettant de créer un plat
     * @param name nom du plat à creer
     * @param description description du plat à creer
     * @param price prix du plat à creer
     * @return true si le plat a pu être supprimé
     */
    public String createDish(String name, String description, String price) {
        JSONObject result = new JSONObject();
        String idOfNewDish = dishRepo.createDish(name, description, price);
        result.put("id", Integer.parseInt(idOfNewDish));
        return result.toString();
    }
}
