package com.switchfully.service.order.dtos;

import com.switchfully.domain.user.User;

import java.util.List;

public class OrderDTO {


    private String orderId;

    private List<ItemGroupDTO> itemGroupDTOList;

    private double orderPrice;

    private String userId;

    public OrderDTO setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderDTO setItemGroupDTOList(List<ItemGroupDTO> itemGroupDTOList) {
        this.itemGroupDTOList = itemGroupDTOList;
        return this;
    }

    public OrderDTO setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
        return this;
    }

    public  OrderDTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<ItemGroupDTO> getItemGroupDTOList() {
        return itemGroupDTOList;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public String getUserId() {
        return userId;
    }
}
