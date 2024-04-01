package fr.univamu.iut.rapidamanger.dish;

/**
 * Classe représentant un plat
 */
public class Dish {

    /**
     * Id du plat
     */
    protected Integer id;

    /**
     * nom du plat
     */
    protected String name;

    /**
     * Description du plat
     */
    protected String description;

    /**
     * Prix du plat
     */
    protected String price;

    /**
     * Constructeur par défaut
     */
    public Dish(){
    }

    /**
     * Constructeur du plat
     * @param id id du plat
     * @param name nom du plat
     * @param description description du plat
     * @param price prix du plat
     */
    public Dish(String id, String name, String description, String price){
        this.id = Integer.parseInt(id);
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * Méthode permettant d'accéder à l'id du plat
     * @return un chaîne de caractères avec lid du plat
     */
    public Integer getId() {
        return id;
    }

    /**
     * Méthode permettant d'accéder au nom du plat
     * @return un chaîne de caractères avec le nom du plat
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode permettant d'accéder à la description du plat
     * @return un chaîne de caractères avec la description du plat
     */
    public String getDescription() {
        return description;
    }

    /**
     * Méthode permettant d'accéder au prix du plat
     * @return un caractère indiquant le prix du plat
     */
    public String getPrice() {
        return price;
    }

    /**
     * Méthode permettant de modifier l'id du plat
     * @param id une chaîne de caractères avec l'id à utiliser
     */
    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    /**
     * Méthode permettant de modifier le nom du plat
     * @param name une chaîne de caractères avec le nom à utiliser
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Méthode permettant de modifier la description du plat
     * @param description une chaîne de caractères avec la description du plat
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Méthode permettant de modifier le du plat
     * @param price une chaîne de caractère avec le prix du plat
     */
    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}