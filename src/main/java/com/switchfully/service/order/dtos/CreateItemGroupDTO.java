package com.switchfully.service.order.dtos;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateItemGroupDTO that)) return false;
        return Objects.equals(getSelectedItemId(), that.getSelectedItemId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSelectedItemId());
    }
}
