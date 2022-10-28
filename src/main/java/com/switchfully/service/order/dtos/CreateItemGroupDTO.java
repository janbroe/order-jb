package com.switchfully.service.order.dtos;

public class CreateItemGroupDTO {
    private String selectedItemId;
    private int amount;

    public CreateItemGroupDTO setSelectedItemId(String selectedItemId) {
        this.selectedItemId = selectedItemId;
        return this;
    }

    public CreateItemGroupDTO setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getSelectedItemId() {
        return selectedItemId;
    }

    public int getAmount() {
        return amount;
    }
}
