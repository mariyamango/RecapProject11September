package org.example;

import java.util.List;

public interface OrderRepo {
    public List<Order> getOrdertList();

    public Order getOrderById(int id);

    public void addOrder(Order order);

    public void removeOrder(int id);
}
