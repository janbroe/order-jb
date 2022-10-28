package com.switchfully.service.order;

import com.switchfully.domain.order.ItemGroup;
import com.switchfully.service.order.dtos.CreateItemGroupDTO;
import com.switchfully.service.order.dtos.ItemGroupDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ItemGroupMapper {

    public ItemGroupDTO itemGroupToDTO(ItemGroup itemGroup) {
        return new ItemGroupDTO()
                .setSelectedItemId(itemGroup.getSelectedItemId())
                .setAmount(itemGroup.getAmount())
                .setShippingDate(itemGroup.getShippingDate());
    }

    public List<ItemGroupDTO> itemGroupToDTO(List<ItemGroup> itemGroupList) {
        return itemGroupList.stream()
                .map(this::itemGroupToDTO)
                .collect(Collectors.toList());
    }

    public ItemGroup DTOtoItemGroup(CreateItemGroupDTO createItemGroupDTO) {
        return new ItemGroup(createItemGroupDTO.getSelectedItemId(), createItemGroupDTO.getAmount());
    }

    public List<ItemGroup> DTOtoItemGroup(List<CreateItemGroupDTO> createItemGroupDTOList) {
        return createItemGroupDTOList.stream()
                .map(this::DTOtoItemGroup)
                .collect(Collectors.toList());
    }
}
