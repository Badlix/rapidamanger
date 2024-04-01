package fr.univamu.iut.rapidamanger.dish;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;

@Path("/dish")
public class DishResource {

    /**
     * Service utilisé pour accéder aux données des plats et récupérer/modifier leurs informations
     */
    private DishService service;

    /**
     * Constructeur par défaut
     */
    public DishResource(){}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param dishRepo objet implémentant l'interface d'accès aux données
     */
    public DishResource( DishRepositoryInterface dishRepo ){
        this.service = new DishService( dishRepo) ;
    }

    /**
     * Constructeur permettant d'initialiser le service d'accès aux plats
     */
    public DishResource( DishService service ){
        this.service = service;
    }

    /**
     * Enpoint permettant de publier de tous les plats enregistrés
     * @return la liste des plats (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public Response getAllDishs() {
        return Response.ok(service.getAllDishsJSON()).build();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getDish(@PathParam("id") String id) {

        String result = service.getDishJSON(id);

        // si le plat n'a pas été trouvé
        if( result == null )
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(result).build();
    }

    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public Response deleteDish(@PathParam("id") String id) {

        String result = service.deleteDish(id);

        // si le plat n'a pas été trouvé
        if( result == null )
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(result).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createDish(String dishJson) {
        JSONObject obj = new JSONObject(dishJson);

        String result = service.createDish(obj.getString("login"), obj.getString("description"), obj.getString("price"));

        // si la création a échoué
        if( result == null )
            return Response.status(Response.Status.BAD_REQUEST).build();

        return Response.ok(result).build();
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateDish(@PathParam("id") String id, String dishJson) {
        JSONObject newValueOfDish = new JSONObject(dishJson);
        JSONObject currentDish = new JSONObject(service.getDishJSON(id));

        // Initiate with the current value of the dish
        String name = currentDish.getString("login");
        String description = currentDish.getString("description");
        String price = currentDish.getString("price");

        // Change the value present in the body of the PUT request with the new value
        if (newValueOfDish.has("login")) {
            name = newValueOfDish.getString("login");
        }
        if (newValueOfDish.has("description")) {
            description = newValueOfDish.getString("description");
        }
        if (newValueOfDish.has("price")) {
            price = newValueOfDish.getString("price");
        }

        String result = service.updateDish(id, name, description, price);

        // si le plat n'a pas été trouvé ou que la modification a échoué
        if( result == null )
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(result).build();
    }

}