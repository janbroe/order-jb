package com.switchfully.service.order;

import com.switchfully.domain.order.Order;
import com.switchfully.service.order.dtos.OrderDTO;

public class OrderMapper {

    private final ItemGroupMapper itemGroupMapper;

    public OrderMapper() {
        this.itemGroupMapper = new ItemGroupMapper();
    }

    public OrderDTO orderToDTO(Order order) {
        return new OrderDTO()
                .setOrderId(order.getId())
                .setItemGroupDTOList(itemGroupMapper.itemGroupToDTO(order.getItemGroupList()))
                .setOrderPrice(order.getOrderPrice())
                .setUser(order.getUser());
    }
}
