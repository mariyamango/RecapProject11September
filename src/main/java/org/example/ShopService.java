package org.example;

import static org.example.Main.*;

public class ShopService {
    private ProductRepo productRepo;
    private OrderRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public String toString() {
        return "ShopService [orderRepo=" + orderRepo + "]";
    }

    public void placeNewOrder(Order order) {
        for (Product product : order.getProductQuantity().keySet()) {
            if (productRepo.getProductById(product.id()) == null) {
                System.out.println("Product " + product.name() + " does not exist.");
                return;
            }
        }
        orderRepo.addOrder(order);
        System.out.println(ANSI_GREEN_BOLD + "Order with id " + order.getId() + " placed successfully. Total price: " + order.getTotalPrice() + ANSI_RESET);
    }

    public void updateProductQuantity(int orderId, Product product, int newQuantity) {
        Order order = orderRepo.getOrderById(orderId);
        if (order == null) {
            System.out.println("Order with id " + orderId + " does not exist.");
        }
        order.setProductQuantity(product, newQuantity);
        System.out.println(ANSI_GREEN_BOLD + "Order with id " + orderId + " updated successfully. Total price: " + order.getTotalPrice() + ANSI_RESET);
    }
}
