import java.util.InputMismatchException;
import java.util.Scanner;
//Ran Aviv 208212662lecturer
//Elad Balisia 207378696
//Tal Kadichivitch 209223593
// lecturer- Keren Klifa
public class Main {

    public static String enterName(MarketManager marketManger, Scanner s, boolean isSeller) {
        String nameType = isSeller ? "seller" : "buyer";
        String name = null;
        boolean duplicate = true;
        System.out.println("Please enter name of new " + nameType + ":");
        while (duplicate) {
            try {
                name = s.nextLine();
                duplicate = marketManger.enterName(name, isSeller);
                if (duplicate) {
                    throw new Exception("There is already a name in the system, please choose again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid string.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return name;
    }

    public static String enterPassword(Scanner s) {
        System.out.println("Enter password:");
        return s.nextLine();
    }

    public static int chooseUser(MarketManager marketManger, Scanner s, boolean isSeller) {
        boolean checkUserExist = false;
        int index = -1;
        while (!checkUserExist) {
            try {
                System.out.print("Please enter " + (isSeller ? "seller" : "buyer") + " name: ");
                String user = s.nextLine();

                if (isSeller) {
                    index = marketManger.indexOfSeller(user);
                } else {
                    index = marketManger.indexOfBuyer(user);
                }

                if (index < 0) {
                    throw new Exception("The User does not exist.");
                }

                checkUserExist = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid string.");
                s.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return index;
    }

    public static int chooseProduct(MarketManager marketManger, Scanner s, int sellerIndex) {
        boolean checkProductExist = false;
        int index = -1;
        while (!checkProductExist) {
            try {
                System.out.print("Please enter Product name: ");
                String productName = s.nextLine();
                index = marketManger.indexOfProduct(sellerIndex, productName);
                if (index < 0) {
                    throw new Exception("This product does not exist in the seller list.");
                }
                checkProductExist = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid string.");
                s.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return index;
    }
    public static String enterNewProduct(MarketManager marketManger, Scanner s, int sellerIndex) {
        boolean checkProductExist = true;
        int index = -1;
        String productName = "";
        while (checkProductExist) {
            try {
                System.out.print("Please enter Product name: ");
                productName = s.nextLine();
                index = marketManger.indexOfProduct(sellerIndex, productName);
                if (index >= 0) {
                    throw new Exception("This product already exist in the seller list please choose again.");
                }
                checkProductExist = false;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid string.");
                s.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return productName;
    }

    public static void printSellers(Seller[] sellers) {
        for (Seller seller : sellers) {
            if (seller != null) {
                System.out.println(seller);
            }
        }
    }

    public static void printBuyers(Buyer[] buyers) {
        for (Buyer buyer : buyers) {
            if (buyer != null) {
                System.out.println(buyer);
            }
        }
    }

    public static void printProducts(Product[] products) {
        for (Product product : products) {
            if (product != null) {
                System.out.println("     " + product);
            }
        }
    }

    public static void printCardFromHistory(Cart[] historyCarts) {
        System.out.println("Carts from history:");
        for (int i = 0; i < historyCarts.length; i++) {
            Cart cart = historyCarts[i];
            if (cart != null) {
                System.out.println((i + 1) + ". " + cart);
            }
        }
    }

    public static void printNames(MarketManager marketManger, boolean isSeller) {
        String[] names;
        if (isSeller) {
            System.out.println("Sellers names:");
            names = marketManger.getUserNameOfSellers();
        } else {
            System.out.println("Buyers names:");
            names = marketManger.getUserNameOfBuyers();
        }
        for (String name : names) {
            if (name != null) {
                System.out.println("   Name: " + name);
            }
        }
    }

    public static int checkInt(Scanner s, String msg, int maximum) {
        boolean fNumOk = false;
        int intNumber = 0;
        while (!fNumOk) {
            try {
                if (msg != null) {
                    System.out.println(msg);
                } else {
                    System.out.println("Enter int number:");
                }
                intNumber = s.nextInt();

                if (intNumber < 0) {
                    throw new Exception("Int number must be greater than 0.");
                }
                if (maximum > 0 && intNumber > maximum) {
                    throw new Exception("This number is greater than the options.");
                }
                s.nextLine();
                fNumOk = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid int number.");
                s.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                s.nextLine();
            }
        }
        return intNumber;
    }

    public static double checkDouble(Scanner s, String msg) {
        boolean fNumOk = false;
        double doubleNumber = 0;
        while (!fNumOk) {
            try {
                if (msg != null) {
                    System.out.println(msg);
                } else {
                    System.out.println("Enter double number:");
                }
                doubleNumber = s.nextDouble();
                s.nextLine();
                if (doubleNumber < 0) {
                    throw new Exception("Double number must be greater than 0.");
                }
                fNumOk = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid double number.");
                s.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                s.nextLine();
            }
        }
        return doubleNumber;
    }

    public static boolean checkYesNo(Scanner s, String msg) {
        boolean validInput = false;
        boolean result = false;
        while (!validInput) {
            try {
                if (msg != null) {
                    System.out.println(msg);
                } else {
                    System.out.println("Enter yes or no:");
                }
                String choice = s.nextLine().trim().toLowerCase();

                if (!choice.equals("yes") && !choice.equals("no")) {
                    throw new IllegalArgumentException("Please enter 'yes' or 'no'.");
                }

                result = choice.equals("yes");
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid string.");
                s.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        return result;
    }

    public static void printCategories(Product.Category[] categories) {
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i]);
        }
    }

    public static void addSeller(MarketManager marketManger, Scanner s) {
        System.out.println("Add seller");
        String nameSeller = enterName(marketManger, s, true);
        String passwordSeller = enterPassword(s);
        marketManger.addSeller(nameSeller, passwordSeller);
        System.out.println("Seller added successfully!");
    }
    public static String enterStringsForAddress(Scanner s, String msg ) {
        boolean validInput = false;
        String name = "";
        while (!validInput) {
            try {

                System.out.println(msg);

                name = s.nextLine();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid string.");
            }
            catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        return name;
    }
    public static void addBuyer(MarketManager marketManger, Scanner s) {
        System.out.println("Add buyer");
        String nameBuyer = enterName(marketManger, s, false);
        String passwordBuyer = enterPassword(s);

        String city = enterStringsForAddress( s, "please enter city");
        String country = enterStringsForAddress( s, "please enter country");
        String street = enterStringsForAddress( s, "please enter street");
        int buildingNum = checkInt(s, "please enter building number:", 0);
        marketManger.addBuyer(nameBuyer, passwordBuyer, city, country, street, buildingNum);
        System.out.println("Buyer added successfully!");
    }

    public static void addProductToSeller(MarketManager marketManger, Scanner s) {

        System.out.println("Add product to Seller");
        printNames(marketManger, true);
        int indexSeller = chooseUser(marketManger, s, true);


        String nameProduct = enterNewProduct( marketManger,  s,  indexSeller);

        double priceProduct = checkDouble(s, "Enter price of product:");
        Product.Category[] categories = Product.Category.values();

        printCategories(categories);
        int categoryChoice = checkInt(s, "Please choose a category:", categories.length );

        Product.Category category = categories[categoryChoice - 1];
        boolean packagingChoice = checkYesNo(s, "whould you like to add specail package?(yes/no)");
        double packagingPrice = 0;
        if (packagingChoice) {
            packagingPrice = checkDouble(s, "Enter packaging price:");
        }
        marketManger.addProductToSeller(indexSeller, nameProduct, priceProduct, category, packagingChoice, packagingPrice);

        System.out.println("Added successfully to the seller!");

    }

    public static void addProductToBuyerCart(MarketManager marketManger, Scanner s) {
        System.out.println("Add product to buyer cart");
        printNames(marketManger, false);
        int indexBuyer = chooseUser(marketManger, s, false);
        printNames(marketManger, true);
        int indexSeller = chooseUser(marketManger, s, true);
        Product[] productsOfSeller = marketManger.getAllProductsOfSeller(indexSeller);
        System.out.println("Products of seller:");
        printProducts(productsOfSeller);
        int indexProduct = chooseProduct(marketManger, s, indexSeller);
        marketManger.addProductToBuyer(indexBuyer, indexSeller, indexProduct);
        System.out.println("Added successfully to the cart!");
    }

    public static void payForBuyerCart(MarketManager marketManger, Scanner s)  {
        System.out.println("Pay for Buyer's cart");
        printNames(marketManger, false);
        int indexBuyer = chooseUser(marketManger, s, false);
        double totalPrice;
        try {
            totalPrice = marketManger.payShoppingCart(indexBuyer);
            System.out.println("Total Price: " + totalPrice);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void printAllBuyers(MarketManager marketManger) {
        System.out.println("Print Buyers");
        Buyer[] buyers = marketManger.getBuyers();
        printBuyers(buyers);
    }

    public static void printAllSellers(MarketManager marketManger) {
        System.out.println("Print Sellers");
        Seller[] sellers = marketManger.getSellers();
        printSellers(sellers);
    }

    public static void displayProductsByCategory(MarketManager marketManger, Scanner s) {
        System.out.println("Display products by category");
        System.out.println("Please choose a category:");
        Product.Category[] categories = Product.Category.values();
        printCategories(categories);
        int categoryChoice = checkInt(s, "Please choose a category:", categories.length);

        Product.Category chosenCategory = categories[categoryChoice - 1];
        marketManger.displayProductsByCategory(chosenCategory);
    }

    public static void createNewCartFromHistory(MarketManager marketManger, Scanner s) {
        System.out.println("Create a new cart from shopping cart history");
        printNames(marketManger, false);
        int indexBuyer = chooseUser(marketManger, s, false);
        boolean checkIfCartEmpty = marketManger.checkIfCartEmpty(indexBuyer);
        if (checkIfCartEmpty) {
            boolean replaceChoice = checkYesNo(s, "Current cart is not empty. Do you want to replace it with a cart from history?");
            if (!replaceChoice) {
                System.out.println("Current cart not replaced.");
                return;
            }
        }
        Cart[] historyCarts = marketManger.getHistoryOfCartsByIndexBuyer(indexBuyer);
        printCardFromHistory(historyCarts);
        int cartChoice = checkInt(s, "Choose number of cart you want to take:", historyCarts.length);
        boolean currentCartReplace = marketManger.replaceCurrentCartFromCartInHistory(indexBuyer, cartChoice - 1);
        if (currentCartReplace)
            System.out.println("New cart created from history successfully.");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int choice;
        boolean fContinue = true;
        MarketManager marketManger = new MarketManager();

        do {
            System.out.println("\nChoose one of the following options:");
            System.out.println("0 - Exit");
            System.out.println("1 - Add seller");
            System.out.println("2 - Add buyer");
            System.out.println("3 - Add product to seller");
            System.out.println("4 - Add product to buyer's cart");
            System.out.println("5 - Pay for buyer's cart");
            System.out.println("6 - Print buyers");
            System.out.println("7 - Print sellers");
            System.out.println("8 - View products by category");
            System.out.println("9 - Create a new cart from shopping cart history");

            choice = checkInt(s, "Enter your choice -->", 9);

            switch (choice) {
                case 0:
                    System.out.println("Exiting the program...");
                    fContinue = false;
                    break;
                case 1:
                    addSeller(marketManger, s);
                    break;
                case 2:
                    addBuyer(marketManger, s);
                    break;
                case 3:
                    addProductToSeller(marketManger, s);
                    break;
                case 4:
                    addProductToBuyerCart(marketManger, s);
                    break;
                case 5:
                    payForBuyerCart(marketManger, s);
                    break;
                case 6:
                    printAllBuyers(marketManger);
                    break;
                case 7:
                    printAllSellers(marketManger);
                    break;
                case 8:
                    displayProductsByCategory(marketManger, s);
                    break;
                case 9:
                    createNewCartFromHistory(marketManger, s);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        } while (fContinue);
        s.close();
    }
}
