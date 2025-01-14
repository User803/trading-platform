package com.app.Trading.platform.services;

import com.app.Trading.platform.model.TwoFactorOTP;
import com.app.Trading.platform.model.User;

public interface TwoFactorOTPService {

    TwoFactorOTP createTwoFactorOTP(User user, String otp, String jwt);
    TwoFactorOTP findUserById(String userId);
    TwoFactorOTP findById(Long userId);

    boolean verifyTwoFactorOTP(TwoFactorOTP twoFactorOTP, String otp);
    void deleteTwoFactorOTP(TwoFactorOTP twoFactorOTP);
}
