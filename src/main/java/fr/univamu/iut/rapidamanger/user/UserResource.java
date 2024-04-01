package fr.univamu.iut.rapidamanger.user;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;

@Path("/user")
public class UserResource {

    /**
     * Service utilisé pour accéder aux données des plats et récupérer/modifier leurs informations
     */
    private UserService service;

    /**
     * Constructeur par défaut
     */
    public UserResource(){}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param userRepo objet implémentant l'interface d'accès aux données
     */
    public UserResource(UserRepositoryInterface userRepo ){
        this.service = new UserService( userRepo) ;
    }

    /**
     * Constructeur permettant d'initialiser le service d'accès aux plats
     */
    public UserResource(UserService service ){
        this.service = service;
    }

    /**
     * Enpoint permettant de publier de tous les plats enregistrés
     * @return la liste des plats (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public Response getAllUsers() {
        return Response.ok(service.getAllUsersJSON()).build();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getUser(@PathParam("id") String reference){

        String result = service.getUserJSON(reference);

        // si l'utilisateur n'a pas été trouvé
        if( result == null )
            Response.status(Response.Status.NOT_FOUND);

        return Response.ok(result).build();
    }

    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public Response deleteDish(@PathParam("id") String reference) {
        String result = service.deleteUser(reference);

        // si l'utilisateur n'a pas été trouvé
        if( result == null )
            Response.status(Response.Status.NOT_FOUND);

        return Response.ok(result).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createUser(String userJson) {
        JSONObject obj = new JSONObject(userJson);

        String result = service.createUser(obj.getString("login"), obj.getString("password"), obj.getString("address"));

        return Response.ok(result).build();
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUser(@PathParam("id") String id, String userJson) {
        JSONObject newValueOfUser = new JSONObject(userJson);
        JSONObject currentUser = new JSONObject(service.getUserJSON(id));

        // Initiate with the current value of the dish
        String name = currentUser.getString("login");
        String password = null; // we initialiaze at null because the GET function doesn't give the password for security reasons
        String address = currentUser.getString("address");

        // Change the value present in the body of the PUT request with the new value
        if (newValueOfUser.has("login")) {
            name = newValueOfUser.getString("login");
        }
        if (newValueOfUser.has("password")) {
            password = newValueOfUser.getString("password");
        }
        if (newValueOfUser.has("address")) {
            address = newValueOfUser.getString("address");
        }

        String result = service.updateUser(id, name, password, address);

        // si le plat n'a pas été trouvé ou que la modification a échoué
        if( result == null )
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(result).build();
    }
}