package com.switchfully.api;

import com.switchfully.domain.user.Feature;
import com.switchfully.service.item.ItemService;
import com.switchfully.service.item.dtos.CreateItemDTO;
import com.switchfully.service.item.dtos.ItemDTO;
import com.switchfully.service.security.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/items")
public class ItemController {

    private final Logger log = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;

    private final SecurityService securityService;

    public ItemController(ItemService itemService, SecurityService securityService) {
        this.itemService = itemService;
        this.securityService = securityService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getAllItems(@RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.GET_ALL_ITEMS);
        log.info("GET -> get all items");
        return itemService.getAllItems();
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO addItem(@RequestHeader String authorization, @RequestBody CreateItemDTO createItemDTO) {
        securityService.validateAuthorization(authorization, Feature.ADD_ITEM);
        log.info("POST -> add item " + createItemDTO.toString());
        return itemService.addItem(createItemDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateItem(@RequestHeader String authorization, @PathVariable("id") String itemId, @RequestBody CreateItemDTO createItemDTO) {
        securityService.validateAuthorization(authorization, Feature.UPDATE_ITEM);
        log.info("PUT -> update item " + createItemDTO.toString());
        itemService.updateItem(itemId, createItemDTO);
    }
}
