package com.app.Trading.platform.services;

import com.app.Trading.platform.domain.PaymentMethod;
import com.app.Trading.platform.model.PaymentOrder;
import com.app.Trading.platform.model.User;
import com.app.Trading.platform.response.PaymentResponse;

public interface PaymentOrderService {

    PaymentOrder createOrder(User user, Long amount, PaymentMethod paymentMethod);
    PaymentOrder getPaymentOrderById(Long id);
    boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId);
    PaymentResponse createRazorpayPaymentLink(User user, Long amount);
    PaymentResponse createStripePaymentLink(User user, Long amount, Long orderId);
}
