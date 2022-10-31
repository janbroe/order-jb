package com.switchfully.domain.order;

import java.util.List;
import java.util.UUID;

public class Order {

    private final String orderId;
    private final List<ItemGroup> itemGroupList;
    private final double orderPrice;
    private final String userId;

    public Order(List<ItemGroup> itemGroupList, String userId) {
        this.orderId = UUID.randomUUID().toString();
        this.itemGroupList = itemGroupList;
        this.orderPrice = getSumOfGroupListsInOrder(itemGroupList);
        this.userId = userId;
    }

    private static double getSumOfGroupListsInOrder(List<ItemGroup> itemGroupList) {
        return itemGroupList.stream()
                .map(itemgroup -> itemgroup.getAmount() * itemgroup.getItemPrice())
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public String getId() {
        return orderId;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public String getUserId() {
        return userId;
    }
}
