package com.switchfully.service.itemGroup.dtos;

import com.switchfully.domain.item.Item;
import com.switchfully.service.item.dtos.ItemDTO;

import java.util.Objects;

public class CreateItemGroupDTO {
    private Long itemId;
    private int amount;

    public CreateItemGroupDTO setSelectedItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public CreateItemGroupDTO setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateItemGroupDTO that)) return false;
        return getAmount() == that.getAmount() && Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, getAmount());
    }

    public Long getSelectedItemId() {
        return itemId;
    }
}
