public class Product {
    private static int counter = 0;
    private String name;
    private Double price;
    private int id;
    private Category category;

    public enum Category {Children, Electricity, Office, Clothing}

    public Product(String name, Double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.id = ++counter;
    }

    public Product(Product other) {
        this.name = other.getName();
        this.price = other.getPrice();
        this.category = other.getCategory();
        this.id = other.getId();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", serialNumber=" + id +
                ", category=" + category +
                '}';
    }
}
