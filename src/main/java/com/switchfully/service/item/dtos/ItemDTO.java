package com.switchfully.service.item.dtos;

public class ItemDTO {

    private Long id;
    private String name;
    private String description;
    private double price;
    private int amount;

    public ItemDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ItemDTO setName(String name) {
        this.name = name;
        return this;
    }

    public ItemDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemDTO setPrice(double price) {
        this.price = price;
        return this;
    }

    public ItemDTO setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public Long getId() {
        return id;
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
