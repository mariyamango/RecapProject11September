package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ShopServiceTest {

    @Test
    void shouldPlaceNewOrderCorrectlyIfAllProductsExist() {
        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderListRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);

        Product productA = new Product(1, "Product A", new BigDecimal("100.0"),"Description of Product A");
        Product productB = new Product(2, "Product B", new BigDecimal("50.0"),"Description of Product B");
        productRepo.addProduct(productA);
        productRepo.addProduct(productB);

        Map<Product, Integer> products = new HashMap<>();
        products.put(productA, 2);
        products.put(productB, 3);
        Order order = new Order(1, products);

        shopService.placeNewOrder(order);

        assertThat(orderRepo.getOrderById(1)).isEqualTo(order);
        assertThat(orderRepo.getOrderById(1).getTotalPrice()).isEqualTo(new BigDecimal("350.0"));
    }

    @Test
    void shouldNotPlaceNewOrderIfProductsDoNotExist() {
        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderListRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);

        Map<Product, Integer> products = new HashMap<>();
        Product productA = new Product(1, "Product A", new BigDecimal("100.0"),"Description of Product A");
        products.put(productA, 2);

        Order order = new Order(1, products);

        shopService.placeNewOrder(order);

        assertThat(orderRepo.getOrderById(1)).isEqualTo(null);
    }

    @Test
    void shouldUpdateProductQuantityCorrectly() {
        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderListRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);

        Product productA = new Product(1, "Product A", new BigDecimal("100.0"),"Description of Product A");
        productRepo.addProduct(productA);

        Map<Product, Integer> products = new HashMap<>();
        products.put(productA, 2);
        Order order = new Order(1, products);
        shopService.placeNewOrder(order);

        shopService.updateProductQuantity(1, productA, 5);

        assertThat(orderRepo.getOrderById(1)).isNotNull();
        assertThat(orderRepo.getOrderById(1).getTotalPrice()).isEqualTo(new BigDecimal("500.0"));
    }
}