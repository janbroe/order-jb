package com.switchfully.service.item;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.service.item.dtos.CreateItemDTO;
import com.switchfully.service.item.dtos.ItemDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository) {
        this.itemMapper = new ItemMapper();
        this.itemRepository = itemRepository;
    }

    public ItemDTO addItem(CreateItemDTO createItemDTO) {
        Item newItem = new Item(createItemDTO.getName(), createItemDTO.getDescription(), createItemDTO.getPrice(), createItemDTO.getAmount());
        itemRepository.save(newItem);
        return itemMapper.itemToDTO(newItem);
    }

    public void updateItem(Long itemId, CreateItemDTO createItemDTO) {
        Item foundItem = itemRepository.findById(itemId).orElseThrow(() -> new NoSuchElementException("The item with id " + itemId + " does not exist"));
        foundItem.setName(createItemDTO.getName());
        foundItem.setDescription(createItemDTO.getDescription());
        foundItem.setPrice(createItemDTO.getPrice());
        foundItem.setAmount(createItemDTO.getAmount());
        itemRepository.save(foundItem);
    }

    public List<ItemDTO> getAllItems() {
        return itemMapper.itemToDTO(itemRepository.findAll());
    }
}
