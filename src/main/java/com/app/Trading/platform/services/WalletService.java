package com.app.Trading.platform.services;

import com.app.Trading.platform.model.Order;
import com.app.Trading.platform.model.User;
import com.app.Trading.platform.model.Wallet;

public interface WalletService {
    Wallet getUserWallet(User user);

    Wallet addBalance(Wallet wallet, Long amount);

    Wallet findWalletById(Long id);

    Wallet walletToWalletTransfer(User sender, Wallet receiver, Long amount);

    Wallet orderPayment(Order order, User user);

}
