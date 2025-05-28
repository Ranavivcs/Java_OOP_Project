public class SpecialPackage extends Product {
    private Double packagingPrice;

    public SpecialPackage(String name, Double price, Category category, double packagingPrice) {
        super(name, price, category);
        this.packagingPrice = packagingPrice;
    }

    public SpecialPackage(SpecialPackage other) {
        super(other);
        this.packagingPrice = other.packagingPrice; // Correctly copy the packagingPrice
    }

    public Double getPackagingPrice() {
        return this.packagingPrice;
    }

    public void setPackagingPrice(Double packagingPrice) {
        this.packagingPrice = packagingPrice;
    }

    @Override
    public String toString() {
        return super.toString() + ", packagingPrice=" + packagingPrice;
    }
}
