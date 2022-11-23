package com.switchfully.domain.order;

import com.switchfully.domain.item.Item;

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
    private Long selectedItemId;
    private int amount;
    private double itemPrice;
    private LocalDate shippingDate;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    public ItemGroup() {
    }

    public ItemGroup(int amount, Item item) {
        this.selectedItemId = item.getId();
        this.amount = amount;
        this.itemPrice = item.getPrice();
        if(item.getAmount() > amount) {
            this.shippingDate = LocalDate.now().plusDays(1);
        } else {
            this.shippingDate = LocalDate.now().plusWeeks(1);
        }
    }

    public Long getSelectedItemId() {
        return selectedItemId;
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
}
