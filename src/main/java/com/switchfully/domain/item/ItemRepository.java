package com.switchfully.domain.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


@Repository
public class ItemRepository {

    private final Logger log = LoggerFactory.getLogger(ItemRepository.class);
    private final Map<String, Item> itemRepository;

    public ItemRepository() {
        this.itemRepository = new HashMap<>();
        Item newItem = new Item("firework", "nice exploding stuff", 34.95, 5);
        addItem(newItem);
        log.info("newItemID: " + newItem.getId());
        Item newItem2 = new Item("popcorn", "nice fluffy stuff", 6, 3);
        addItem(newItem2);
        log.info("newItemID: " + newItem2.getId());
    }

    public void addItem(Item item) {
        itemRepository.put(item.getId(), item);
    }

    public Item getItemById(String itemId) {
        doesItemExist(itemId);
        return itemRepository.get(itemId);
    }

    public void doesItemExist(String itemId) {
        if (!itemRepository.containsKey(itemId)) {
            log.error("The item with id " + itemId + " does not exist");
            throw new NoSuchElementException("The item with id " + itemId + " does not exist");
        }
    }

//    public boolean isNumberOfItemsInStock(String itemId, int amount) {
//        doesItemExist(itemId);
//        if (getItemById(itemId).getAmount() < amount) {
//            throw new NoSuchElementException("The item with id " + itemId + " has insufficient stock");
//        }
//        return true;
//    }
}
