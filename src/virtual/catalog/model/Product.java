package virtual.catalog.model;

public class Product {
    private long id;
    private String name;
    private double price;
    private int id_category;
    private String description;
    private String image;

    public Product(long id, String name, double price, int id_category, String description, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.id_category = id_category;
        this.description = description;
        this.image = image;
    }

    public Product(long id, String name, double price, String description, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public Product(String name, double price, int id_category, String description, String image) {
        this.name = name;
        this.price = price;
        this.id_category = id_category;
        this.description = description;
        this.image = image;
    }

    public Product(String name, double price, String description, String image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public Product() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
