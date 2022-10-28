package com.switchfully.service.order;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.domain.order.*;
import com.switchfully.service.order.dtos.CreateOrderDTO;
import com.switchfully.service.order.dtos.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final ItemGroupMapper itemGroupMapper;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = new OrderMapper();
        this.itemGroupMapper = new ItemGroupMapper();
    }

    public OrderDTO orderItems(CreateOrderDTO createOrderDTO) {
        //checks on item needs to be done here and the item then should be provided to the ItemGroup Constructor
        //mapper needs to be done with two parameters one DTO and one item?
        Order newOrder = new Order(itemGroupMapper.DTOtoItemGroup(createOrderDTO.getCreateItemGroupDTOList()));
        orderRepository.addOrder(newOrder);
        return orderMapper.orderToDTO(newOrder);
    }
}
