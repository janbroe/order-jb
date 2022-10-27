package com.switchfully.domain.item;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepository {

    private final Map<String, Item> itemRepository;

    public ItemRepository() {
        this.itemRepository = new HashMap<>();
        addItem(new Item("firework", "nice exploding stuff", 34.95, 5));
    }

    public void addItem(Item item) {
        itemRepository.put(item.getId(), item);
    }
}
