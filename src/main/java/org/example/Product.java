package org.example;

import java.math.BigDecimal;

public record Product(int id, String name, BigDecimal price, String description) {

}
