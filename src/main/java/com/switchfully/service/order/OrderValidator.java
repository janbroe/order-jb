package com.switchfully.service.order;

import com.switchfully.domain.exceptions.MultipleItemGroupsWithSameIdInOrderException;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.service.itemGroup.dtos.CreateItemGroupDTO;
import com.switchfully.service.order.dtos.CreateOrderDTO;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
@Service
public class OrderValidator {

    private final ItemRepository itemRepository;

    public OrderValidator(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void checkRequiredFields(CreateOrderDTO createOrderDTO) {
        checkOnDuplicateItemGroupIds(createOrderDTO);
        checkIfOrderExistAndItemIsInStock(createOrderDTO);
    }

    private static void checkOnDuplicateItemGroupIds(CreateOrderDTO createOrderDTO) {
        //check if there are multiple ItemGroups with the same itemId by checking the normal list size with the distinct list size
        int distinctListSize = createOrderDTO.getCreateItemGroupDTOList().stream()
                .distinct()
                .toList()
                .size();

        if (createOrderDTO.getCreateItemGroupDTOList().size() > distinctListSize) {
            throw new MultipleItemGroupsWithSameIdInOrderException();
        }
    }

    private void checkIfOrderExistAndItemIsInStock(CreateOrderDTO createOrderDTO) {
        for (CreateItemGroupDTO createItemGroupDTO : createOrderDTO.getCreateItemGroupDTOList()) {
            itemRepository.findById(createItemGroupDTO.getSelectedItemId()).orElseThrow(() -> new NoSuchElementException("The item with id " + createItemGroupDTO.getSelectedItemId() + "does not exist"));
        }
    }
}
