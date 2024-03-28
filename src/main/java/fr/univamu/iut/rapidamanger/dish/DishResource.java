package fr.univamu.iut.rapidamanger.dish;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

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
    public String getAllDishs() {
        Jsonb jsonb = JsonbBuilder.create();
        return service.getAllDishsJSON();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getDish
}