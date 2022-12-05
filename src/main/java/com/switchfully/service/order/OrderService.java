package com.switchfully.service.order;

import com.switchfully.domain.exceptions.MultipleItemGroupsWithSameIdInOrderException;
import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.domain.itemGroup.ItemGroup;
import com.switchfully.domain.itemGroup.ItemGroupRepository;
import com.switchfully.domain.order.*;
import com.switchfully.domain.user.User;
import com.switchfully.service.itemGroup.ItemGroupMapper;
import com.switchfully.service.itemGroup.ItemGroupService;
import com.switchfully.service.itemGroup.dtos.CreateItemGroupDTO;
import com.switchfully.service.order.dtos.CreateOrderDTO;
import com.switchfully.service.order.dtos.OrderDTO;
import com.switchfully.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    private final UserService userService;
    private final OrderMapper orderMapper;
    private final ItemGroupMapper itemGroupMapper;

    private final ItemGroupService itemGroupService;

    private final OrderValidator orderValidator;

    private final ItemGroupRepository itemGroupRepository;


    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository, ItemGroupService itemGroupService, UserService userService, OrderValidator orderValidator, ItemGroupRepository itemGroupRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.itemGroupService = itemGroupService;
        this.userService = userService;
        this.orderValidator = orderValidator;
        this.itemGroupRepository = itemGroupRepository;
        this.orderMapper = orderMapper;
        this.itemGroupMapper = new ItemGroupMapper();
    }

    public OrderDTO orderItems(CreateOrderDTO createOrderDTO, String userName) {

        orderValidator.checkRequiredFields(createOrderDTO);
        //user
        User foundUser = userService.findUserByUsername(userName);
        //orderprice
        double orderPrice = createOrderDTO.getCreateItemGroupDTOList().stream()
                .map(createItemGroupDTO -> getPrice(createItemGroupDTO) * createItemGroupDTO.getAmount())
                .mapToDouble(Double::doubleValue)
                .sum();

        Order createdOrder = new Order(orderPrice, foundUser);

        List<ItemGroup> createdItemGroupList = createOrderDTO.getCreateItemGroupDTOList().stream()
                .map(itemGroup -> new ItemGroup(itemGroup, createdOrder))
                .toList();

        createdItemGroupList.stream().map(itemGroup -> itemGroupRepository.save(itemGroup));

//        List<Item> itemList = getItemList(createOrderDTO);

////        Order newOrder = new Order(itemGroupMapper.DTOtoItemGroup(createOrderDTO.getCreateItemGroupDTOList(), itemList), foundUser);
//        List<ItemGroup> newItemGroupList = itemGroupService.createItemGroupList(createOrderDTO.getCreateItemGroupDTOList());
//
//        Order newOrder = new Order(itemGroupMapper.DTOtoItemGroup(createOrderDTO.getCreateItemGroupDTOList(), itemList), foundUser);
//
//        Order newOrder = new Order(foundUser, )
//
//        private static double getSumOfGroupListsInOrder(List<ItemGroup> itemGroupList) {
//            return itemGroupList.stream()
//                    .map(itemgroup -> itemgroup.getAmount() * itemgroup.getItemPrice())
//                    .mapToDouble(Double::doubleValue)
//                    .sum();
//        }
        orderRepository.save(createdOrder);

        decreaseAmountInItemRepository(createOrderDTO);

        return orderMapper.orderToDTO(createdOrder);

    }

    private double getPrice(CreateItemGroupDTO createItemGroupDTO) {
        return itemRepository.findById(createItemGroupDTO.getSelectedItemId())
                .orElseThrow(() -> new IllegalArgumentException("Price for Id " + createItemGroupDTO.getSelectedItemId() + " not found"))
                .getPrice();
    }

    private void decreaseAmountInItemRepository(CreateOrderDTO createOrderDTO) {
        for (CreateItemGroupDTO createItemGroupDTO : createOrderDTO.getCreateItemGroupDTOList()) {
            itemRepository.findById(createItemGroupDTO.getSelectedItemId())
                    .orElseThrow(() -> new NoSuchElementException("The item with id " + createItemGroupDTO.getSelectedItemId() + "does not exist"))
                    .reduceAmount(createItemGroupDTO.getAmount());
        }
    }

    private List<Item> getItemList(CreateOrderDTO createOrderDTO) {
        return createOrderDTO.getCreateItemGroupDTOList().stream()
                .map(createItemGroupDTO -> itemRepository.findById(createItemGroupDTO.getSelectedItemId()).orElseThrow(() -> new NoSuchElementException("The item with id does not exist")))
                .toList();
    }

    public List<OrderDTO> getAllOrders() {
        return orderMapper.orderToDTO(orderRepository.findAll());
    }
}
