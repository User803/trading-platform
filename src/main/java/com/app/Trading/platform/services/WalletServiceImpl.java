package com.app.Trading.platform.services;

import com.app.Trading.platform.domain.OrderType;
import com.app.Trading.platform.model.Order;
import com.app.Trading.platform.model.User;
import com.app.Trading.platform.model.Wallet;
import com.app.Trading.platform.repositories.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class WalletServiceImpl implements WalletService{

    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet getUserWallet(User user) {
        Wallet wallet = walletRepository.findByUserId(user.getId());

        if (wallet == null) {
            wallet = new Wallet();
            wallet.setUser(user);
        }
        return wallet;
    }

    @Override
    public Wallet addBalance(Wallet wallet, Long amount) {
        BigDecimal balance = wallet.getBalance();
        BigDecimal newBalance = balance.add(BigDecimal.valueOf(amount));
        wallet.setBalance(newBalance);

        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findWalletById(Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);

        if (wallet.isPresent()) {
            return wallet.get();
        }

        throw new RuntimeException("wallet not found");
    }

    @Override
    public Wallet walletToWalletTransfer(User sender, Wallet receiver, Long amount) {
        Wallet senderWallet = getUserWallet(sender);

        if (senderWallet.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        BigDecimal senderBalance = senderWallet.getBalance()
                .subtract(BigDecimal.valueOf(amount));
        senderWallet.setBalance(senderBalance);

        BigDecimal receiverBalance = receiver.getBalance()
                .add(BigDecimal.valueOf(amount));
        receiver.setBalance(receiverBalance);

        walletRepository.save(receiver);

        return senderWallet;
    }

    @Override
    public Wallet orderPayment(Order order, User user) {
        Wallet wallet = getUserWallet(user);

        if (order.getOrderType().equals(OrderType.BUY)){
            BigDecimal newBalance = wallet.getBalance().subtract(order.getPrice());

            if (newBalance.compareTo(order.getPrice()) < 0) {
                throw new RuntimeException("Insufficient funds for this transaction");
            }
            wallet.setBalance(newBalance);
        } else {
            BigDecimal newBalance = wallet.getBalance().add(order.getPrice());
            wallet.setBalance(newBalance);
        }
        walletRepository.save(wallet);

        return wallet;
    }
}
