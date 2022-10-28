package com.switchfully.service.order.dtos;

import java.util.List;

public class CreateOrderDTO {

    private List<CreateItemGroupDTO> createItemGroupDTOList;

    public CreateOrderDTO setCreateItemGroupDTOList(List<CreateItemGroupDTO> createItemGroupDTOList) {
        this.createItemGroupDTOList = createItemGroupDTOList;
        return this;
    }

    public List<CreateItemGroupDTO> getCreateItemGroupDTOList() {
        return createItemGroupDTOList;
    }
}
