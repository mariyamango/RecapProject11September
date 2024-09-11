package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Create Product, ProductRepo, manipulations with ProductRepo:");
        Product milk = new Product(1, "Milk", 0.99,"Milk produced by MilkCompany");
        Product bread = new Product(2, "Bread", 1.19,"Bread produced by BreadCompany");
        Product cheese = new Product(3, "Cheese", 0.89,"Cheese produced by CheeseCompany");
        Product cake = new Product(4, "Cake", 2.99,"Cake produced by CakeCompany");
        Product sausage = new Product(5, "Sausage", 1.24,"Sausage produced by SausageCompany");
        Product eggs = new Product(6, "Eggs", 1.95,"10 eggs produced by EggCompany");
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

        ShopService shopServiceMap = new ShopService(orderMapRepo);
        ShopService shopServiceList = new ShopService(orderListRepo);

        Order order1 = new Order(1, List.of(milk, bread));
        Order order2 = new Order(2, List.of(cheese, sausage));
        Order order3 = new Order(3, List.of(milk,cake,sausage));

        shopServiceMap.createOrder(order1);
        shopServiceMap.createOrder(order3);
        System.out.println("Orders in shopServiceMap:\n" + shopServiceMap);
        shopServiceList.createOrder(order2);
        shopServiceList.createOrder(order3);
        System.out.println("Orders in shopServiceList:\n" + shopServiceList);

    }
}