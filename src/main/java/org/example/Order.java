package org.example;

import java.math.BigDecimal;
import java.util.Map;

public class Order {

    private int id;
    private Map<Product, Integer> productQuantity;

    public Order(int id, Map<Product, Integer> productQuantity) {
        this.id = id;
        this.productQuantity = productQuantity;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Product product : productQuantity.keySet()) {
            totalPrice = totalPrice.add(product.price().multiply(new BigDecimal(productQuantity.get(product))));
        }
        return totalPrice;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productQuantity=" + productQuantity +
                '}';
    }

    public Map<Product, Integer> getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Product product, int quantity) {
        productQuantity.put(product, quantity);
    }
}
