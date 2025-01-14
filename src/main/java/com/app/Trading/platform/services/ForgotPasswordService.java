package com.app.Trading.platform.services;

import com.app.Trading.platform.domain.VerificationType;
import com.app.Trading.platform.model.ForgotPasswordToken;
import com.app.Trading.platform.model.User;

public interface ForgotPasswordService {

    ForgotPasswordToken createToken(User user,
                                    String id,
                                    String otp,
                                    VerificationType verificationType,
                                    String sendTo);

    ForgotPasswordToken findById(String id);
    ForgotPasswordToken findByUser(Long userId);
    void deleteToken(ForgotPasswordToken token);
}
