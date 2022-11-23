package com.switchfully.service.order.dtos;

import com.switchfully.domain.user.User;

import java.util.List;

public class OrderDTO {


    private Long orderId;

    private List<ItemGroupDTO> itemGroupDTOList;

    private double orderPrice;

    private User user;

    public OrderDTO setOrderId(Long orderId) {
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

    public  OrderDTO setUser(User user) {
        this.user = user;
        return this;
    }

    public Long getOrderId() {
        return orderId;
    }

    public List<ItemGroupDTO> getItemGroupDTOList() {
        return itemGroupDTOList;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public User getUser() {
        return user;
    }
}
