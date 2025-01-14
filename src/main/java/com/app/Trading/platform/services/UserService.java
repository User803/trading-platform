package com.app.Trading.platform.services;

import com.app.Trading.platform.domain.VerificationType;
import com.app.Trading.platform.model.User;

public interface UserService {

    User findUserProfileByJwt(String jwt);
    User findUserByEmail(String email);
    User findUserById(Long userId);
    User enableTwoFactorAuth(VerificationType verificationType,
                             String sendTo,
                             User user);
    User updatePassword(User user, String newPassword);
}
