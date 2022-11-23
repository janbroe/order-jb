package com.switchfully.service.order;

import com.switchfully.domain.exceptions.MultipleItemGroupsWithSameIdInOrderException;
import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.domain.order.*;
import com.switchfully.service.order.dtos.CreateItemGroupDTO;
import com.switchfully.service.order.dtos.CreateOrderDTO;
import com.switchfully.service.order.dtos.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ItemRepository itemRepository;
    private final OrderMapper orderMapper;

    private final ItemGroupMapper itemGroupMapper;

    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.orderMapper = new OrderMapper();
        this.itemGroupMapper = new ItemGroupMapper();
    }

    public OrderDTO orderItems(CreateOrderDTO createOrderDTO, String userId) {

        checkOnDuplicateItemGroupIds(createOrderDTO);

        checkIfOrderExistAndItemIsInStock(createOrderDTO);

        List<Item> itemList = getItemList(createOrderDTO);

        Order newOrder = new Order(itemGroupMapper.DTOtoItemGroup(createOrderDTO.getCreateItemGroupDTOList(), itemList));

        orderRepository.save(newOrder);

        decreaseAmountInItemRepository(createOrderDTO);

        return orderMapper.orderToDTO(newOrder);

    }

    private void decreaseAmountInItemRepository(CreateOrderDTO createOrderDTO) {
        for (CreateItemGroupDTO createItemGroupDTO : createOrderDTO.getCreateItemGroupDTOList()) {
            itemRepository.findById(createItemGroupDTO.getSelectedItemId())
                    .orElseThrow(() -> new NoSuchElementException("The item with id " + createItemGroupDTO.getSelectedItemId() + "does not exist"))
                    .reduceAmount(createItemGroupDTO.getAmount());
        }
    }

    private void checkIfOrderExistAndItemIsInStock(CreateOrderDTO createOrderDTO) {
        for (CreateItemGroupDTO createItemGroupDTO : createOrderDTO.getCreateItemGroupDTOList()) {
            itemRepository.findById(createItemGroupDTO.getSelectedItemId()).orElseThrow(() -> new NoSuchElementException("The item with id " + createItemGroupDTO.getSelectedItemId() + "does not exist"));
        }
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

    private List<Item> getItemList(CreateOrderDTO createOrderDTO) {
        return createOrderDTO.getCreateItemGroupDTOList().stream()
                .map(createItemGroupDTO -> itemRepository.findById(createItemGroupDTO.getSelectedItemId()).orElseThrow(() -> new NoSuchElementException("The item with id does not exist")))
                .toList();
    }
}
