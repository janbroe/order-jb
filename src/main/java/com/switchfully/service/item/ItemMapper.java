package com.switchfully.service.item;

import com.switchfully.domain.item.Item;
import com.switchfully.service.item.dtos.ItemDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ItemMapper {

    public ItemDTO itemToDTO(Item item) {
        return new ItemDTO()
                .setId(item.getId())
                .setName(item.getName())
                .setDescription(item.getDescription())
                .setPrice(item.getPrice())
                .setAmount(item.getAmount());
    }

    public List<ItemDTO> itemToDTO(List<Item> items) {
        return items.stream()
                .map(this::itemToDTO)
                .collect(Collectors.toList());
    }
}
