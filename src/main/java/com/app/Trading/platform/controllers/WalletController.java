package com.app.Trading.platform.controllers;

import com.app.Trading.platform.model.Order;
import com.app.Trading.platform.model.User;
import com.app.Trading.platform.model.Wallet;
import com.app.Trading.platform.model.WalletTransaction;
import com.app.Trading.platform.services.OrderService;
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
    private final OrderService orderService;

    public WalletController(WalletService walletService, UserService userService, OrderService orderService) {
        this.walletService = walletService;
        this.userService = userService;
        this.orderService = orderService;
    }


    //    ** 5:15:25 URL
    @GetMapping
    public ResponseEntity<Wallet> getUserWallet(@RequestParam("Authorization") String jwt) {
        User user = userService.findUserProfileByJwt(jwt);
        Wallet wallet = walletService.getUserWallet(user);

        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{walletId}/transfer")
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

    @PutMapping("/order/{orderId}/pay")
    public ResponseEntity<Wallet> payOrderPayment(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long orderId) {

        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.getOrderById(orderId);
        Wallet wallet = walletService.orderPayment(order, user);

        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }
}
