package org.example;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class OrderListRepoTest {

    @Test
    void shouldGetOrderListCorrectly() {
        OrderListRepo orderRepo = new OrderListRepo();
        Map<Product, Integer> productsA = new HashMap<>();
        Map<Product, Integer> productsB = new HashMap<>();
        Product productA = new Product(1, "Product A", new BigDecimal("100.0"),"Description of Product A");
        Product productB = new Product(2, "Product B", new BigDecimal("50.0"),"Description of Product A");
        productsA.put(productA, 1);
        productsB.put(productB, 3);

        Order orderA = new Order(1, productsA);
        Order orderB = new Order(2, productsB);
        orderRepo.addOrder(orderA);
        orderRepo.addOrder(orderB);

        assertThat(orderRepo.getOrdertList()).hasSize(2);
        assertThat(orderRepo.getOrdertList()).extracting(Order::getId)
                .containsExactlyInAnyOrder(1, 2);
    }

    @Test
    void shouldAddOrderCorrectly() {
        OrderListRepo orderRepo = new OrderListRepo();
        Map<Product, Integer> products = new HashMap<>();
        Product productA = new Product(1, "Product A", new BigDecimal("100.0"),"Description of Product A");
        products.put(productA, 2);

        Order order = new Order(1, products);
        orderRepo.addOrder(order);

        assertThat(orderRepo.getOrderById(1)).isEqualTo(order);
        assertThat(orderRepo.getOrderById(1).getProductQuantity().get(productA)).isEqualTo(2);
    }

    @Test
    void shouldRemoveOrderCorrectly() {
        OrderListRepo orderRepo = new OrderListRepo();
        Map<Product, Integer> products = new HashMap<>();
        Product productA = new Product(1, "Product A", new BigDecimal("100.0"),"Description of Product A");
        products.put(productA, 2);

        Order order = new Order(1, products);
        orderRepo.addOrder(order);
        orderRepo.removeOrder(1);

        assertThat(orderRepo.getOrderById(1)).isEqualTo(null);
    }
}