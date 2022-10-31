package com.switchfully.domain.order;

import com.switchfully.domain.item.Item;

import java.time.LocalDate;

public class ItemGroup {
    private final String selectedItemId;
    private final int amount;
    private final LocalDate shippingDate;

    public ItemGroup(int amount, Item item) {
        this.selectedItemId = item.getId();
        this.amount = amount;
        if(item.getAmount() > amount) {
            this.shippingDate = LocalDate.now().plusDays(1);
        } else {
            this.shippingDate = LocalDate.now().plusWeeks(1);
        }
    }

    public String getSelectedItemId() {
        return selectedItemId;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }
}
