import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Cart {
    private Product[] products;
    private double sumPrice;
    private int logicLengthProducts;
    private LocalDateTime date;

    public Cart() {
        this.logicLengthProducts = 0;
        this.products = new Product[0];
        this.sumPrice = 0;
    }

    public Cart(Cart otherCart) {
        this.logicLengthProducts = otherCart.logicLengthProducts;
        this.sumPrice = otherCart.sumPrice;
        this.products = new Product[otherCart.products.length];
        for (int i = 0; i < otherCart.products.length; i++) {
            if (otherCart.getProducts()[i] != null)
                this.products[i] = new Product(otherCart.getProducts()[i]);
        }
    }

    public void addProductToCart(Product product) {
        if (this.logicLengthProducts == this.products.length) {
            Product[] newArr = Arrays.copyOf(this.products, (this.logicLengthProducts + 1) * 2);
            this.products = newArr;
        }
        this.products[this.logicLengthProducts] = product;
        this.logicLengthProducts++;
        this.addProductToSumPrice(product.getPrice());
    }

    public double getSumPrice() {
        return this.sumPrice;
    }

    private void addProductToSumPrice(double priceOfProduct) {
        this.sumPrice += priceOfProduct;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public String getDateInFormat() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.date.format(dtf);
    }

    public void setDate() {
        this.date = LocalDateTime.now();
    }

    public Product[] getProducts() {
        return this.products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cart{ ").append(date != null ? "date=" + getDateInFormat() + ", " : "").append("sumPrice=").append(sumPrice).append(", products=[\n");
        for (int i = 0; i < logicLengthProducts; i++) {
            if (i > 0) sb.append(", \n");
            sb.append(products[i]);
        }
        sb.append("\n]}");
        return sb.toString();
    }
}
