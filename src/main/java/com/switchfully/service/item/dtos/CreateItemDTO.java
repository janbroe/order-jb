package com.switchfully.service.item.dtos;

public class CreateItemDTO {

    private String name;
    private String description;
    private double price;
    private int amount;

    public CreateItemDTO setName(String name) {
        this.name = name;
        return this;
    }

    public CreateItemDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public CreateItemDTO setPrice(double price) {
        this.price = price;
        return this;
    }

    public CreateItemDTO setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}
