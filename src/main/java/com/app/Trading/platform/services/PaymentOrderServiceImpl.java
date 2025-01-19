package com.app.Trading.platform.services;

import com.app.Trading.platform.domain.PaymentMethod;
import com.app.Trading.platform.model.PaymentOrder;
import com.app.Trading.platform.model.User;
import com.app.Trading.platform.repositories.PaymentOrderRepository;
import com.app.Trading.platform.response.PaymentResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentOrderServiceImpl implements PaymentOrderService{

    private final PaymentOrderRepository paymentOrderRepository;

    public PaymentOrderServiceImpl(PaymentOrderRepository paymentOrderRepository) {
        this.paymentOrderRepository = paymentOrderRepository;
    }

    @Override
    public PaymentOrder createOrder(User user, Long amount, PaymentMethod paymentMethod) {
        return null;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) {
        return null;
    }

    @Override
    public boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId) {
        return false;
    }

    @Override
    public PaymentResponse createRazorpayPaymentLink(User user, Long amount) {
        return null;
    }

    @Override
    public PaymentResponse createStripePaymentLink(User user, Long amount, Long orderId) {
        return null;
    }
}
