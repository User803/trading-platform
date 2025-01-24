package com.app.Trading.platform.model;

import com.app.Trading.platform.domain.WalletTransactionType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private WalletTransactionType transactionType;
    private LocalDate date;
    private String transferId;
    private String purpose;
    private Long amount;

    @ManyToOne
    private Wallet wallet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WalletTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(WalletTransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        WalletTransaction that = (WalletTransaction) o;
        return Objects.equals(id, that.id) && transactionType == that.transactionType && Objects.equals(date, that.date) && Objects.equals(transferId, that.transferId) && Objects.equals(purpose, that.purpose) && Objects.equals(amount, that.amount) && Objects.equals(wallet, that.wallet);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(transactionType);
        result = 31 * result + Objects.hashCode(date);
        result = 31 * result + Objects.hashCode(transferId);
        result = 31 * result + Objects.hashCode(purpose);
        result = 31 * result + Objects.hashCode(amount);
        result = 31 * result + Objects.hashCode(wallet);
        return result;
    }
}
