package org.example;

import java.math.BigDecimal;
import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductRepo productRepo = new ProductRepo();
    private static final OrderRepo orderRepo = new OrderListRepo();
    private static final ShopService shopService = new ShopService(productRepo, orderRepo);

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String ANSI_RED_BOLD = "\033[1;31m";    // RED
    public static final String ANSI_GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String ANSI_YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String ANSI_BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String ANSI_PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String ANSI_CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String ANSI_WHITE_BOLD = "\033[1;37m";

    public static final String ANSI_RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String ANSI_GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String ANSI_YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String ANSI_BLUE_UNDERLINED = "\033[4;34m";

    public static void main(String[] args) {

        boolean on = true;

        while (on) {
            printMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1" -> createNewProduct();
                case "2" -> removeProduct();
                case "3" -> printProducts();
                case "4" -> createNewOrder();
                case "5" -> printOrders();
                case "6" -> changeQuantityInProductOfOrder();
                case "7" -> on = false;
                default -> System.out.println(ANSI_RED_BOLD + "Incorrect option! Please try again." + ANSI_RESET);
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println(ANSI_BLUE_BOLD + "\nWelcome to Command-line Product Management Tool! Choose an option:" + ANSI_RESET);
        System.out.println(ANSI_BLUE_BOLD + "1. " + ANSI_RESET + "Add a product");
        System.out.println(ANSI_BLUE_BOLD + "2. " + ANSI_RESET + "Remove a product");
        System.out.println(ANSI_BLUE_BOLD + "3. " + ANSI_RESET + "Print a list of all the products");
        System.out.println(ANSI_BLUE_BOLD + "4. " + ANSI_RESET + "Place an order");
        System.out.println(ANSI_BLUE_BOLD + "5. " + ANSI_RESET + "Print a list of all the orders");
        System.out.println(ANSI_BLUE_BOLD + "6. " + ANSI_RESET + "Modify a quantity of product in an order");
        System.out.println(ANSI_BLUE_BOLD + "7. " + ANSI_RESET + "Exit");
        System.out.print(ANSI_BLUE_UNDERLINED + "Your option: " + ANSI_RESET);
    }

    private static void createNewProduct() {
        System.out.print("Enter a new product identifier: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter a new product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter a new product price: ");
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));

        System.out.print("Enter a new product description: ");
        String description = scanner.nextLine();

        Product product = new Product(id, name, price, description);
        productRepo.addProduct(product);

        System.out.println(ANSI_GREEN_BOLD + "Product added successfully." + ANSI_RESET);
    }

    private static void removeProduct() {
        System.out.print("Enter a product identifier to remove: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (productRepo.getProductById(id) == null) {
            System.out.println(ANSI_RED_BOLD + "Product not found." + ANSI_RESET);
            return;
        }
        productRepo.removeProduct(id);
        System.out.println(ANSI_GREEN_BOLD + "Product is removed successfully." + ANSI_RESET);
    }

    private static void printProducts() {
        List<Product> products = productRepo.getProductList();
        if (products.isEmpty()) {
            System.out.println(ANSI_YELLOW_BOLD + "No products in the list." + ANSI_RESET);
        } else {
            System.out.print(ANSI_CYAN_BOLD + "Available Products:\n" + ANSI_RESET);
            products.forEach(product -> System.out.println(ANSI_CYAN + product.id() + ": \t" + product.name() + "\t - " + product.price() + "\t - " + product.description() + ANSI_RESET));
        }
    }

    private static void createNewOrder() {
        System.out.print("Enter a new order identifier: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        Map<Product, Integer> orderProducts = new HashMap<>();
        while (true) {
            System.out.print("Enter product identifier to add to a new order. Or type \"done\" to finish: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            int productId = Integer.parseInt(input);
            Product productOpt = productRepo.getProductById(productId);
            if (productOpt != null) {
                System.out.print("Enter quantity for " + productOpt.name() + ": ");
                int quantity = Integer.parseInt(scanner.nextLine());
                orderProducts.put(productOpt, quantity);
            } else {
                System.out.println(ANSI_RED_BOLD + "Product not found." + ANSI_RESET);
            }
        }
        Order order = new Order(orderId, orderProducts);
        shopService.placeNewOrder(order);
    }

    private static void printOrders() {
        List<Order> orders = orderRepo.getOrdertList();
        if (orders.isEmpty()) {
            System.out.println(ANSI_YELLOW_BOLD + "No orders in the list." + ANSI_RESET);
        } else {
            orders.forEach(order -> {
                System.out.println(ANSI_CYAN_BOLD + "Order id: " + order.getId() + ANSI_RESET);
                System.out.println(ANSI_CYAN + "Products:" + ANSI_RESET);
                order.getProductQuantity().forEach((product, quantity) ->
                        System.out.println(ANSI_CYAN + "\t" + product.name() + "\t x" + quantity + "\t - " + product.price() + ANSI_RESET));
                System.out.println(ANSI_CYAN_BOLD + "Total Price: \t" + order.getTotalPrice() + ANSI_RESET);
            });
        }
    }

    private static void changeQuantityInProductOfOrder() {
        System.out.print("Enter an order identifier to modify: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter product identifier to modify its quantity: ");
        int productId = Integer.parseInt(scanner.nextLine());

        Product productOpt = productRepo.getProductById(productId);
        if (productOpt != null) {
            System.out.print("Enter new quantity: ");
            int newQuantity = Integer.parseInt(scanner.nextLine());
            shopService.updateProductQuantity(orderId, productOpt, newQuantity);
        } else {
            System.out.println(ANSI_RED_BOLD + "Product is not found." + ANSI_RESET);
        }
    }
}