package com.app.Trading.platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double quantity;
    private double buyPrice;
    private double sellPrice;

    @ManyToOne
    private Coin coin;

    @JsonIgnore
    @OneToOne
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;
        return Double.compare(quantity, orderItem.quantity) == 0 && Double.compare(buyPrice, orderItem.buyPrice) == 0 && Double.compare(sellPrice, orderItem.sellPrice) == 0 && Objects.equals(id, orderItem.id) && Objects.equals(coin, orderItem.coin) && Objects.equals(order, orderItem.order);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Double.hashCode(quantity);
        result = 31 * result + Double.hashCode(buyPrice);
        result = 31 * result + Double.hashCode(sellPrice);
        result = 31 * result + Objects.hashCode(coin);
        result = 31 * result + Objects.hashCode(order);
        return result;
    }
}
