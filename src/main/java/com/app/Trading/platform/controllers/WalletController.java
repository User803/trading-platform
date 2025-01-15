package com.app.Trading.platform.controllers;

import com.app.Trading.platform.model.User;
import com.app.Trading.platform.model.Wallet;
import com.app.Trading.platform.model.WalletTransaction;
import com.app.Trading.platform.services.UserService;
import com.app.Trading.platform.services.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;
    private final UserService userService;

    public WalletController(WalletService walletService, UserService userService) {
        this.walletService = walletService;
        this.userService = userService;
    }

//    ** 5:15:25 URL
    @GetMapping
    public ResponseEntity<Wallet> getUserWallet(@RequestParam("Authorization") String jwt) {
        User user = userService.findUserProfileByJwt(jwt);
        Wallet wallet = walletService.getUserWallet(user);

        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Wallet> walletToWalletTransfer(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long walletId,
            @RequestBody WalletTransaction request) {

        User senderUser = userService.findUserProfileByJwt(jwt);
        Wallet receiverWallet = walletService.findWalletById(walletId);
        Wallet wallet = walletService.walletToWalletTransfer(
                senderUser,
                receiverWallet,
                request.getAmount()
        );

        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }
}
