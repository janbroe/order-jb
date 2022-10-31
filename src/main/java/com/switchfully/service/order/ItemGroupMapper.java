package com.switchfully.service.order;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.order.ItemGroup;
import com.switchfully.service.order.dtos.CreateItemGroupDTO;
import com.switchfully.service.order.dtos.ItemGroupDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemGroupMapper {

    private final Logger log = LoggerFactory.getLogger(ItemGroupMapper.class);

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

    public ItemGroup DTOtoItemGroup(CreateItemGroupDTO createItemGroupDTO, Item item) {
        return new ItemGroup(createItemGroupDTO.getAmount(), item);
    }

    public List<ItemGroup> DTOtoItemGroup(List<CreateItemGroupDTO> createItemGroupDTOList, List<Item> itemList) {
        if (createItemGroupDTOList.size() != itemList.size()) {
            log.error("Size of createItemGroupDTOList and itemList are not the same");
            throw new IllegalArgumentException("Size of createItemGroupDTOList and itemList are not the same");
        }

        List<ItemGroup> resultItemGroupList = new ArrayList<>();

        for (int index = 0; index < createItemGroupDTOList.size(); index++) {
            resultItemGroupList.add(index, DTOtoItemGroup(createItemGroupDTOList.get(index), itemList.get(index)));
        }

        return resultItemGroupList;
    }
}
