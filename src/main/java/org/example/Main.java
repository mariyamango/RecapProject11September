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


    }
}