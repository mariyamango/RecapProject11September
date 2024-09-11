package org.example;

import java.util.ArrayList;
import java.util.List;

public class OrderListRepo implements OrderRepo{
    private List<Order> orders;

    public OrderListRepo(List<Order> orders) {
        this.orders = new ArrayList<Order>(orders);
    }

    public OrderListRepo() {
        this.orders = new ArrayList<Order>();
    }

    public List<Order> getOrdertList() {
        return orders;
    }

    public Order getOrderById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                orders.remove(order);
                return;
            }
        }
    }

    @Override
    public String toString() {
        if (orders.isEmpty()) {
            return "No orders found.";
        }
        StringBuilder sb = new StringBuilder();
        for (Order order : orders) {
            sb.append(order);
            sb.append("\n");
        }
        return sb.toString();
    }
}
