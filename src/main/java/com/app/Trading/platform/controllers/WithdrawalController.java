package com.app.Trading.platform.controllers;

import com.app.Trading.platform.domain.WalletTransactionType;
import com.app.Trading.platform.model.User;
import com.app.Trading.platform.model.Wallet;
import com.app.Trading.platform.model.WalletTransaction;
import com.app.Trading.platform.model.Withdrawal;
import com.app.Trading.platform.services.UserService;
import com.app.Trading.platform.services.WalletService;
import com.app.Trading.platform.services.WithdrawalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WithdrawalController {

    private final WithdrawalService withdrawalService;
    private final WalletService walletService;
    private final UserService userService;
//    private final WalletTransactionService walletTransactionService;

    public WithdrawalController(WithdrawalService withdrawalService, WalletService walletService, UserService userService) {
        this.withdrawalService = withdrawalService;
        this.walletService = walletService;
        this.userService = userService;
    }

    @PostMapping("/api/withdrawal/{amount}")
    public ResponseEntity<?> withdrawalRequest(
            @PathVariable Long amount,
            @RequestHeader("Authorization") String jwt) {

        User user = userService.findUserProfileByJwt(jwt);
        Wallet userWallet = walletService.getUserWallet(user);

        Withdrawal withdrawal = withdrawalService.requestWithdrawal(amount, user);
        walletService.addBalance(userWallet, -withdrawal.getAmount());

//        WalletTransaction walletTransaction = walletTransactionService.createTransaction(
//                userWallet,
//                WalletTransactionType.WITHDRAWAL,
//                null,
//                "Bank account withdrawal",
//                withdrawal.getAmount()
//        );

        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @PatchMapping("/api/admin/withdrawal/{id}/proceed/{accept}")
    public ResponseEntity<?> proceedWithdrawal(
            @PathVariable Long id,
            @PathVariable boolean accept,
            @RequestHeader("Authorization") String jwt) {

        User user = userService.findUserProfileByJwt(jwt);
        Withdrawal withdrawal = withdrawalService.proceedWithWithdrawal(id, accept);
        Wallet userWallet = walletService.getUserWallet(user);

        if (!accept) {
            walletService.addBalance(userWallet, withdrawal.getAmount());
        }

        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @GetMapping("/api/withdrawal")
    public ResponseEntity<List<Withdrawal>> getWithdrawalHistory(
            @RequestHeader("Authorization") String jwt) {

        User user = userService.findUserProfileByJwt(jwt);
        List<Withdrawal> withdrawals = withdrawalService.getUserWithdrawalHistory(user);

        return new ResponseEntity<>(withdrawals, HttpStatus.OK);
    }

    @GetMapping("/api/admin/withdrawal")
    public ResponseEntity<List<Withdrawal>> getWithdrawalRequests(
            @RequestHeader("Authorization") String jwt) {

        User user = userService.findUserProfileByJwt(jwt);
        List<Withdrawal> withdrawals = withdrawalService.getAllWithdrawalRequests();

        return new ResponseEntity<>(withdrawals, HttpStatus.OK);
    }
}
