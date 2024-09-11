package org.example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        System.out.println("Create Product, ProductRepo, manipulations with ProductRepo:");
        Product milk = new Product(1, "Milk", new BigDecimal("0.99"),"Milk produced by MilkCompany");
        Product bread = new Product(2, "Bread", new BigDecimal("1.19"),"Bread produced by BreadCompany");
        Product cheese = new Product(3, "Cheese", new BigDecimal("0.89"),"Cheese produced by CheeseCompany");
        Product cake = new Product(4, "Cake", new BigDecimal("2.99"),"Cake produced by CakeCompany");
        Product sausage = new Product(5, "Sausage", new BigDecimal("1.24"),"Sausage produced by SausageCompany");
        Product eggs = new Product(6, "Eggs", new BigDecimal("1.95"),"10 eggs produced by EggCompany");
        System.out.println(milk);
        ProductRepo repo = new ProductRepo(List.of(milk, bread, cheese, cake, sausage));
        System.out.println(repo);
        System.out.println(repo.getProductById(4));
        repo.removeProduct(3);
        System.out.println("After removing product with id 3: ");
        repo.getProductList().forEach(System.out::println);
        repo.addProduct(cheese);
        System.out.println("After adding product with id 3: ");
        repo.getProductList().forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

        System.out.println("Manipulations with Map and List and Interface:");
        OrderRepo orderMapRepo = new OrderMapRepo();
        OrderRepo orderListRepo = new OrderListRepo();

        ShopService shopServiceMap = new ShopService(repo, orderMapRepo);
        ShopService shopServiceList = new ShopService(repo, orderListRepo);

        Map<Product,Integer> productMapOrder1 = new HashMap<>();
        productMapOrder1.put(milk, 2);
        productMapOrder1.put(bread, 3);
        Order order1 = new Order (1, productMapOrder1);
        Map<Product,Integer> productMapOrder2 = new HashMap<>();
        productMapOrder2.put(cheese, 4);
        productMapOrder2.put(sausage, 8);
        Order order2 = new Order(2, productMapOrder2);
        Map<Product,Integer> productMapOrder3 = new HashMap<>();
        productMapOrder3.put(milk, 3);
        productMapOrder3.put(cake, 1);
        productMapOrder3.put(sausage, 2);
        Order order3 = new Order(3, productMapOrder3);

        shopServiceMap.placeNewOrder(order1);
        shopServiceMap.placeNewOrder(order3);
        System.out.println("Orders in shopServiceMap:\n" + shopServiceMap);
        shopServiceList.placeNewOrder(order2);
        shopServiceList.placeNewOrder(order3);
        System.out.println("Orders in shopServiceList:\n" + shopServiceList);
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

        System.out.println("Manipulations with prices and quantity:");
        System.out.println("Total price of order number " + order1.getId() + " is: " + order1.getTotalPrice());
        shopServiceMap.updateProductQuantity(1, milk, 1);
    }
}