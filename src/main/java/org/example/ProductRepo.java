package org.example;

import java.util.ArrayList;
import java.util.List;

public class ProductRepo {
    private List<Product> products;

    public ProductRepo(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    public ProductRepo() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProductList() {
        return products;
    }

    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.id() == id) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(int id) {
        for (Product product : products) {
            if (product.id() == id) {
                products.remove(product);
                return;
            }
        }
    }

    @Override
    public String toString() {
        if (products.isEmpty()) {
            return "No products found.";
        }
        StringBuilder sb = new StringBuilder();
        for (Product product : products) {
            sb.append(product);
            sb.append("\n");
        }
        return sb.toString();
    }
}
