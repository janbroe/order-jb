package com.switchfully.service.order;

import com.switchfully.domain.itemGroup.ItemGroupRepository;
import com.switchfully.domain.order.Order;
import com.switchfully.service.itemGroup.ItemGroupMapper;
import com.switchfully.service.order.dtos.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final ItemGroupMapper itemGroupMapper;

    private final ItemGroupRepository itemGroupRepository;

    public OrderMapper(ItemGroupRepository itemGroupRepository) {
        this.itemGroupMapper = new ItemGroupMapper();
        this.itemGroupRepository = itemGroupRepository;
    }

    public OrderDTO orderToDTO(Order order) {
        return new OrderDTO()
                .setOrderId(order.getId())
                .setItemGroupDTOList(itemGroupMapper.itemGroupToDTO(itemGroupRepository.findItemGroupsByOrder(order)))
                .setOrderPrice(order.getOrderPrice())
                .setUser(order.getUser());
    }

    public List<OrderDTO> orderToDTO(Collection<Order> orders) {
        return orders.stream()
                .map(this::orderToDTO)
                .collect(Collectors.toList());
    }
}
