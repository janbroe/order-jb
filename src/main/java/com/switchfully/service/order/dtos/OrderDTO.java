package com.switchfully.service.order.dtos;

import com.switchfully.domain.order.ItemGroup;

import java.time.LocalDate;
import java.util.List;

public class OrderDTO {

    private String orderId;

    private List<ItemGroupDTO> itemGroupDTOList;

    public OrderDTO setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderDTO setItemGroupDTOList(List<ItemGroupDTO> itemGroupDTOList) {
        this.itemGroupDTOList = itemGroupDTOList;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<ItemGroupDTO> getItemGroupDTOList() {
        return itemGroupDTOList;
    }
}
