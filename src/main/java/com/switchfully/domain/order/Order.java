package com.switchfully.domain.order;

import java.util.List;
import java.util.UUID;

public class Order {

    private final String orderId;
    private final List<ItemGroup> itemGroupList;


    public Order(List<ItemGroup> itemGroupList) {
        this.orderId = UUID.randomUUID().toString();;
        this.itemGroupList = itemGroupList;
    }

    public String getId() {
        return orderId;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }
}
