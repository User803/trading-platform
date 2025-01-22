package com.app.Trading.platform.model;

import com.app.Trading.platform.domain.WithdrawalStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Withdrawal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private WithdrawalStatus withdrawalStatus;

    private Long amount;
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WithdrawalStatus getWithdrawalStatus() {
        return withdrawalStatus;
    }

    public void setWithdrawalStatus(WithdrawalStatus withdrawalStatus) {
        this.withdrawalStatus = withdrawalStatus;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

        Withdrawal that = (Withdrawal) o;
        return Objects.equals(id, that.id) && withdrawalStatus == that.withdrawalStatus && Objects.equals(amount, that.amount) && Objects.equals(date, that.date) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(withdrawalStatus);
        result = 31 * result + Objects.hashCode(amount);
        result = 31 * result + Objects.hashCode(date);
        result = 31 * result + Objects.hashCode(user);
        return result;
    }
}
