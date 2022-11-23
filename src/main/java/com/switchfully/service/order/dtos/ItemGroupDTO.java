package com.switchfully.service.order.dtos;

import java.time.LocalDate;

public class ItemGroupDTO {

    private Long selectedItemId;
    private int amount;
    private LocalDate shippingDate;

    public ItemGroupDTO setSelectedItemId(Long selectedItemId) {
        this.selectedItemId = selectedItemId;
        return this;
    }

    public ItemGroupDTO setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemGroupDTO setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }

    public Long getSelectedItemId() {
        return selectedItemId;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }
}
