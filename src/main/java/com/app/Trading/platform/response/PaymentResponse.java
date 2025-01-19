package com.app.Trading.platform.response;

import java.util.Objects;

public class PaymentResponse {
    private String payment_url;

    public String getPayment_url() {
        return payment_url;
    }

    public void setPayment_url(String payment_url) {
        this.payment_url = payment_url;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        PaymentResponse that = (PaymentResponse) o;
        return Objects.equals(payment_url, that.payment_url);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(payment_url);
    }
}
