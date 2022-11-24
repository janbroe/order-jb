package com.switchfully.api;

import com.switchfully.service.item.ItemService;
import com.switchfully.service.item.dtos.CreateItemDTO;
import com.switchfully.service.item.dtos.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/items")
public class ItemController {

    private final Logger log = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;


    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('GET_ALL_ITEMS')")
    public List<ItemDTO> getAllItems() {
        log.info("GET -> get all items");
        return itemService.getAllItems();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADD_ITEM')")
    public ItemDTO addItem(@RequestBody CreateItemDTO createItemDTO) {
        log.info("POST -> add item " + createItemDTO.toString());
        return itemService.addItem(createItemDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('UPDATE_ITEM')")
    public void updateItem(@PathVariable("id") Long itemId, @RequestBody CreateItemDTO createItemDTO) {
        log.info("PUT -> update item " + createItemDTO.toString());
        itemService.updateItem(itemId, createItemDTO);
    }
}
