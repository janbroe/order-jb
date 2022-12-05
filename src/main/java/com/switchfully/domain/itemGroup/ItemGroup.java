package com.switchfully.domain.itemGroup;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.order.Order;
import com.switchfully.service.itemGroup.dtos.CreateItemGroupDTO;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "item_group")
public class ItemGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemgroup_seq")
    @SequenceGenerator(name = "itemgroup_seq", sequenceName = "itemgroup_seq", allocationSize = 1)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="item_id")
    private Item item;
    private int amount;
    private double itemPrice;
    private LocalDate shippingDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="order_id")
    private Order order;

    public ItemGroup() {
    }

    public ItemGroup(int amount, Item item) {
        this.amount = amount;
        this.itemPrice = item.getPrice();
        if(item.getAmount() > amount) {
            this.shippingDate = LocalDate.now().plusDays(1);
        } else {
            this.shippingDate = LocalDate.now().plusWeeks(1);
        }
    }

    public ItemGroup(CreateItemGroupDTO createItemGroupDTO, Order order) {
        this.item = itemReposicreateItemGroupDTO.getSelectedItem();
        this.amount = createItemGroupDTO.getAmount();
        this.itemPrice = createItemGroupDTO.getSelectedItem().getPrice();
        this.order = order;
        if(item.getAmount() > amount) {
            this.shippingDate = LocalDate.now().plusDays(1);
        } else {
            this.shippingDate = LocalDate.now().plusWeeks(1);
        }
    }

    public long getId() {
        return id;
    }
    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public Long getSelectedItemId() {
        return this.item.getId();
    }
}
