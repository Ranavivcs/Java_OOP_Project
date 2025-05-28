public class Buyer extends User implements Comparable<Buyer> {
    private Address address;
    private Cart cart;
    private Cart[] historyOfCarts;
    private int logicLengthHistoryOfCarts;

    public Buyer(String userName, String password, Address address) {
        super(userName, password);
        this.address = address;
        this.logicLengthHistoryOfCarts = 0;
        this.cart = new Cart();
        this.historyOfCarts = new Cart[0];
    }

    public Address getAddress() {
        return this.address;
    }

    public boolean setAddress(Address address) {
        this.address = address;
        return true;
    }

    public Cart getCart() {
        return this.cart;
    }

    public boolean setCart(Cart cart) {
        this.cart = cart;
        return true;
    }

    public Cart[] getHistoryOfCarts() {
        return this.historyOfCarts;
    }

    public boolean payShoppingCart() {
        this.getCart().setDate();
        this.addingCartToHistory(this.cart);
        this.setCart(new Cart());
        return true;
    }

    public boolean addingCartToHistory(Cart cart) {
        if (this.logicLengthHistoryOfCarts == this.historyOfCarts.length) {
            Cart[] newArr = new Cart[(this.logicLengthHistoryOfCarts + 1) * 2];
            for (int i = 0; i < this.historyOfCarts.length; i++)
                newArr[i] = this.historyOfCarts[i];
            newArr[this.logicLengthHistoryOfCarts] = cart;
            this.historyOfCarts = newArr;
            return true;
        }

        this.historyOfCarts[this.logicLengthHistoryOfCarts] = cart;
        this.logicLengthHistoryOfCarts++;
        return true;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Buyer{userName='").append(getUserName()).append("', password='").append(getPassword()).append("\n").append("', address='").append(address).append("\n").append("', cart=").append(cart).append("\n").append(", historyOfCarts=[\n");
        for (int i = 0; i < logicLengthHistoryOfCarts; i++) {
            if (historyOfCarts[i] != null) {
                sb.append((i + 1)).append(". ").append(historyOfCarts[i]).append("\n");
            }
        }
        sb.append("]}");
        return sb.toString();
    }

    @Override
    public int compareTo(Buyer otherBuyer) {
        return this.getUserName().compareTo(otherBuyer.getUserName());
    }
}
