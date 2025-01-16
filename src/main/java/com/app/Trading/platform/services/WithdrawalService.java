package com.app.Trading.platform.services;

import com.app.Trading.platform.model.User;
import com.app.Trading.platform.model.Withdrawal;

import java.util.List;

public interface WithdrawalService {
    Withdrawal requestWithdrawal(Long amount, User user);
    Withdrawal proceedWithWithdrawal(Long withdrawalId, boolean accept);
    List<Withdrawal> getUserWithdrawalHistory(User user);
    List<Withdrawal> getAllWithdrawalRequests();
}
