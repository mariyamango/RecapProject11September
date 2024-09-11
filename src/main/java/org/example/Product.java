package org.example;

public record Product(int id, String name, double price, String description) {

    public int getId() {
        return id;
    }
}
