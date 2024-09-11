package org.example;

import java.math.BigDecimal;
import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductRepo productRepo = new ProductRepo();
    private static final OrderRepo orderRepo = new OrderListRepo();
    private static final ShopService shopService = new ShopService(productRepo, orderRepo);

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
                default -> System.out.println("Incorrect option! Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nWelcome to Command-line Product Management Tool! Choose an option:");
        System.out.println("1. Add a product");
        System.out.println("2. Remove a product");
        System.out.println("3. Print a list of all the products");
        System.out.println("4. Place an order");
        System.out.println("5. Print a list of all the orders");
        System.out.println("6. Modify a quantity of product in an order");
        System.out.println("7. Exit");
        System.out.print("Your option: ");
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

        System.out.println("Product added successfully.");
    }

    private static void removeProduct() {
        System.out.print("Enter a product identifier to remove: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (productRepo.getProductById(id) == null) {
            System.out.println("Product not found.");
            return;
        }
        productRepo.removeProduct(id);
        System.out.println("Product is removed successfully.");
    }

    private static void printProducts() {
        List<Product> products = productRepo.getProductList();
        if (products.isEmpty()) {
            System.out.println("No products in the list.");
        } else {
            System.out.println("Available Products:");
            products.forEach(product -> System.out.println(product.id() + ": \t" + product.name() + "\t - " + product.price() + "\t - " + product.description()));
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
                System.out.println("Product not found.");
            }
        }
        Order order = new Order(orderId, orderProducts);
        shopService.placeNewOrder(order);
    }

    private static void printOrders() {
        List<Order> orders = orderRepo.getOrdertList();
        if (orders.isEmpty()) {
            System.out.println("No orders in the list.");
        } else {
            orders.forEach(order -> {
                System.out.println("Order id: " + order.getId());
                System.out.println("Products:");
                order.getProductQuantity().forEach((product, quantity) ->
                        System.out.println("\t" + product.name() + "\t x" + quantity + "\t - " + product.price()));
                System.out.println("Total Price: \t" + order.getTotalPrice());
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
            System.out.println("Product is not found.");
        }
    }
}