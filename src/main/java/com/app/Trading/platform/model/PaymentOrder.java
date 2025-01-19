package com.app.Trading.platform.model;

import com.app.Trading.platform.domain.PaymentMethod;
import com.app.Trading.platform.domain.PaymentOrderStatus;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class PaymentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amount;
    private PaymentOrderStatus status;
    private PaymentMethod paymentMethod;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public PaymentOrderStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentOrderStatus status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        PaymentOrder that = (PaymentOrder) o;
        return Objects.equals(id, that.id) && Objects.equals(amount, that.amount) && status == that.status && paymentMethod == that.paymentMethod && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(amount);
        result = 31 * result + Objects.hashCode(status);
        result = 31 * result + Objects.hashCode(paymentMethod);
        result = 31 * result + Objects.hashCode(user);
        return result;
    }
}
