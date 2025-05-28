import java.util.Arrays;
import java.util.Comparator;

public class MarketManager {
    private static Seller[] sellers = new Seller[0];
    private static int logicLengthOfSellers = 0;
    private static int physicalSizeOfSellers = 0;
    private static Buyer[] buyers = new Buyer[0];
    private static int logicLengthOfBuyers = 0;
    private static int physicalSizeOfBuyers = 0;

    public static String[] getUserNameOfSellers() {
        String arrayOfUserNameOfSellers[] = new String[sellers.length];
        for (int i = 0; i < sellers.length; i++) {
            if (sellers[i] != null)
                arrayOfUserNameOfSellers[i] = sellers[i].getUserName();
        }
        return arrayOfUserNameOfSellers;
    }

    public static String[] getUserNameOfBuyers() {
        String arrayOfUserNameOfBuyers[] = new String[buyers.length];
        for (int i = 0; i < buyers.length; i++) {
            if (buyers[i] != null)
                arrayOfUserNameOfBuyers[i] = buyers[i].getUserName();
        }
        return arrayOfUserNameOfBuyers;
    }

    public boolean enterName(String name, boolean isSeller) {
        String arr[];
        if (isSeller)
            arr = getUserNameOfSellers();
        else
            arr = getUserNameOfBuyers();

        boolean duplicate = false;
        for (int i = 0; i < arr.length; i++) {
            if (name.equals(arr[i])) {
                duplicate = true;
            }
        }
        return duplicate;
    }

    public static Seller[] addSellerToArray(String name, String password) {
        if (logicLengthOfSellers == physicalSizeOfSellers) {
            Seller[] newArr = new Seller[(logicLengthOfSellers + 1) * 2];
            for (int i = 0; i < sellers.length; i++)
                newArr[i] = sellers[i];
            newArr[logicLengthOfSellers] = new Seller(name, password);
            return newArr;
        }
        sellers[logicLengthOfSellers] = new Seller(name, password);
        return sellers;
    }

    public static void addSeller(String name, String password) {
        sellers = addSellerToArray(name, password);
        logicLengthOfSellers = logicLengthOfSellers + 1;
        physicalSizeOfSellers = sellers.length;
    }

    public static Buyer[] addBuyerToArray(String name, String password, Address address) {
        if (logicLengthOfBuyers == physicalSizeOfBuyers) {
            Buyer[] newArr = new Buyer[(logicLengthOfBuyers + 1) * 2];
            for (int i = 0; i < buyers.length; i++)
                newArr[i] = buyers[i];
            newArr[logicLengthOfBuyers] = new Buyer(name, password, address);
            return newArr;
        }
        buyers[logicLengthOfBuyers] = new Buyer(name, password, address);
        return buyers;
    }

    public static void addBuyer(String name, String password,String city, String country, String street, int buildingNum) {
        Address address = new Address( city,  country,  street,  buildingNum);
        buyers = addBuyerToArray(name, password, address);
        logicLengthOfBuyers++;
        physicalSizeOfBuyers = buyers.length;
    }

    public int indexOfSeller(String seller) {
        int index = -1;
        for (int i = 0; i < sellers.length; i++) {
            if (sellers[i] != null && seller.equals(sellers[i].getUserName()))
                index = i;
        }
        return index;
    }

    public static int indexOfBuyer(String buyer) {
        int index = -1;
        for (int i = 0; i < buyers.length; i++) {
            if (buyers[i] != null && buyer.equals(buyers[i].getUserName()))
                index = i;
        }
        return index;
    }

    public static int indexOfProduct(int indexSeller, String product) {
        int index = -1;
        Product[] productsOfSeller = getAllProductsOfSeller(indexSeller);
        for (int i = 0; i < productsOfSeller.length; i++) {
            if (productsOfSeller[i] != null && product.equals(productsOfSeller[i].getName()))
                index = i;
        }
        return index;
    }

    public static void addProductToSeller(int indexOfSeller, String name, double price, Product.Category category, boolean packagingChoice, double packagingPrice) {
        Product product;
        if (packagingChoice) {
            product = new SpecialPackage(name, price, category, packagingPrice);
        } else {
            product = new Product(name, price, category);
        }
        sellers[indexOfSeller].addProductToSeller(product);

    }

    public static Product[] getAllProductsOfSeller(int indexOfSeller) {
        Product[] productsOfSeller = sellers[indexOfSeller].getProductsOfSeller();
        return productsOfSeller;
    }

    public static void addProductToBuyer(int indexOfBuyer, int indexOfSeller, int indexOfProduct) {
        Product[] productsOfSeller = getAllProductsOfSeller(indexOfSeller);
        Product product = productsOfSeller[indexOfProduct];
        Product copyProduct;
        if (product instanceof SpecialPackage) {
            SpecialPackage specialProduct = (SpecialPackage) product;
            double totalPrice = specialProduct.getPrice();
            totalPrice += specialProduct.getPackagingPrice();
            copyProduct = new SpecialPackage(specialProduct);
            copyProduct.setPrice(totalPrice);
        } else {
            copyProduct = new Product(product);
        }
        buyers[indexOfBuyer].getCart().addProductToCart(copyProduct);
    }

    public static double payShoppingCart(int indexOfBuyer) throws Exception {
        double price = buyers[indexOfBuyer].getCart().getSumPrice();
        if(price == 0)
            throw new Exception("can not pay the cart is empty");
        buyers[indexOfBuyer].payShoppingCart();
        return price;
    }

    public void displayProductsByCategory(Product.Category category) {
        for (Seller seller : sellers) {
            if (seller != null) {
                Product[] products = seller.getProductsOfSeller();
                for (Product product : products) {
                    if (product != null && product.getCategory() == category) {
                        System.out.println("Seller: " + seller.getUserName());
                        System.out.println("Product: " + product.getName() + ", Price: " + product.getPrice());
                    }
                }
            }
        }
    }

    public static Seller[] getSellers() {
        Arrays.sort(sellers, Comparator.nullsLast(Seller::compareTo));
        return sellers;
    }

    public static Buyer[] getBuyers() {
        Arrays.sort(buyers, Comparator.nullsLast(Buyer::compareTo));
        return buyers;
    }

    public boolean checkIfCartEmpty(int indexBuyer) {
        Buyer buyer = getBuyers()[indexBuyer];
        if (buyer.getCart().getProducts().length > 0) {
            return true;
        }
        return false;
    }

    public Cart[] getHistoryOfCartsByIndexBuyer(int indexBuyer) {
        Buyer buyer = getBuyers()[indexBuyer];
        return buyer.getHistoryOfCarts();
    }

    public boolean replaceCurrentCartFromCartInHistory(int indexBuyer, int indexCart) {
        Cart selectedCart = this.buyers[indexBuyer].getHistoryOfCarts()[indexCart];
        this.buyers[indexBuyer].setCart(new Cart(selectedCart));
        return true;
    }
}
