package com.app.Trading.platform.services;

import com.app.Trading.platform.model.PaymentDetails;
import com.app.Trading.platform.model.User;

public interface PaymentDetailsService {

    PaymentDetails addPaymentDetails(String accountNumber,
                                     String accountHolderName,
                                     String ifsc,
                                     String bankName, User user);

    PaymentDetails getUserPaymentDetails(User user);

}
