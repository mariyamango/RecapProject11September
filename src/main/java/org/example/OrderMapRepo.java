package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapRepo implements OrderRepo{
    private Map<Integer,Order> orderMap;

    public OrderMapRepo() {
        orderMap = new HashMap<Integer,Order>();
    }

    @Override
    public List<Order> getOrdertList() {
        return new ArrayList<Order>(orderMap.values());
    }

    @Override
    public Order getOrderById(int id) {
        return orderMap.get(id);
    }

    @Override
    public void addOrder(Order order) {
        orderMap.put(order.id(), order);
    }

    @Override
    public void removeOrder(int id) {
        orderMap.remove(id);
    }

    @Override
    public String toString() {
        if (orderMap.isEmpty()) {
            return "No orders found.";
        }
        StringBuilder sb = new StringBuilder();
        for (Order order : orderMap.values()) {
            sb.append(order);
            sb.append("\n");
        }
        return sb.toString();
    }
}
