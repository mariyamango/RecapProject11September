package org.example;

public class ShopService {
    private OrderRepo orderRepo;

    public ShopService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public void createOrder(Order order) {
        orderRepo.addOrder(order);
    }

    @Override
    public String toString() {
        return "ShopService [orderRepo=" + orderRepo + "]";
    }
}
