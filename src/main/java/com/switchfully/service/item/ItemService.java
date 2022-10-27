package com.switchfully.service.item;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.service.item.dtos.CreateItemDTO;
import com.switchfully.service.item.dtos.ItemDTO;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository) {
        this.itemMapper = new ItemMapper();
        this.itemRepository = itemRepository;
    }

    public ItemDTO addItem(CreateItemDTO createItemDTO) {
        Item newItem = new Item(createItemDTO.getName(), createItemDTO.getDescription(), createItemDTO.getPrice(), createItemDTO.getAmount());
        itemRepository.addItem(newItem);
        return itemMapper.itemToDTO(newItem);
    }
}
