package com.app.Trading.platform.controllers;

import com.app.Trading.platform.domain.PaymentMethod;
import com.app.Trading.platform.model.PaymentOrder;
import com.app.Trading.platform.model.User;
import com.app.Trading.platform.response.PaymentResponse;
import com.app.Trading.platform.services.PaymentOrderService;
import com.app.Trading.platform.services.UserService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentOrderController {

    private final UserService userService;
    private final PaymentOrderService paymentOrderService;

    public PaymentOrderController(UserService userService, PaymentOrderService paymentOrderService) {
        this.userService = userService;
        this.paymentOrderService = paymentOrderService;
    }

    @PostMapping("/api/payment/{paymentMethod}/amount/{amount}")
    public ResponseEntity<PaymentResponse> paymentHandler(
            @PathVariable PaymentMethod paymentMethod,
            @PathVariable Long amount,
            @RequestHeader("Authorization") String jwt) throws RazorpayException, StripeException {

        User user = userService.findUserProfileByJwt(jwt);
        PaymentResponse paymentResponse;

        PaymentOrder order = paymentOrderService.createOrder(user, amount, paymentMethod);

        if (paymentMethod.equals(PaymentMethod.RAZORPAY)) {
            paymentResponse = paymentOrderService.createRazorpayPaymentLink(user, amount, order.getId());
        } else {
            paymentResponse = paymentOrderService.createStripePaymentLink(user, amount, order.getId());
        }

        return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
    }
}
