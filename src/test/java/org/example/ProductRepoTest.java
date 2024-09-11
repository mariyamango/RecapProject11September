package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ProductRepoTest {

    @Test
    void shouldGetProductListCorrectly() {
        ProductRepo productRepo = new ProductRepo();
        Product productA = new Product(1, "Product A", new BigDecimal("100.0"),"Description of Product A");
        Product productB = new Product(2, "Product B", new BigDecimal("200.0"),"Description of Product B");

        productRepo.addProduct(productA);
        productRepo.addProduct(productB);

        assertThat(productRepo.getProductList()).hasSize(2);
        assertThat(productRepo.getProductList()).extracting(Product::name)
                .containsExactlyInAnyOrder("Product A", "Product B");
    }

    @Test
    void shouldAddProductCorrectly() {
        ProductRepo productRepo = new ProductRepo();
        Product product = new Product(1, "Product A", new BigDecimal("100.0"),"Description of Product A");

        productRepo.addProduct(product);

        assertThat(productRepo.getProductById(1)).isEqualTo(product);
        assertThat(productRepo.getProductById(1).name()).isEqualTo("Product A");
    }

    @Test
    void shouldRemoveProductCorrectly() {
        ProductRepo productRepo = new ProductRepo();
        Product product = new Product(1, "Product A", new BigDecimal("100.0"),"Description of Product A");

        productRepo.addProduct(product);
        productRepo.removeProduct(1);

        assertThat(productRepo.getProductById(1)).isEqualTo(null);
    }
}