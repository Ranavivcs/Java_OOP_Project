public class Seller extends User implements Comparable<Seller> {
    private Product[] products;
    private int logicLengthProducts;

    Seller(String userName, String password) {
        super(userName, password);
        this.logicLengthProducts = 0;
        this.products = new Product[0];
    }

    public void addProductToSeller(Product product) {
        if (this.logicLengthProducts == products.length) {
            Product[] newArr = new Product[(this.logicLengthProducts + 1) * 2];
            for (int i = 0; i < products.length; i++)
                newArr[i] = products[i];
            this.products = newArr;
        }
        this.products[this.logicLengthProducts] = product;
        this.logicLengthProducts++;
    }

    public Product[] getProductsOfSeller() {
        return products;
    }

    public int getLogicLengthProducts() {
        return this.logicLengthProducts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Seller{userName='").append(getUserName()).append("', password='").append(getPassword()).append("', products=[\n");
        for (int i = 0; i < logicLengthProducts; i++) {
            if (i > 0) sb.append(", \n");
            sb.append(products[i]);
        }
        sb.append("\n]}");
        return sb.toString();
    }


    @Override
    public int compareTo(Seller otherSeller) {
        return Integer.compare(otherSeller.getLogicLengthProducts(), this.getLogicLengthProducts());
    }
}
