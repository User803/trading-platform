package com.app.Trading.platform.repositories;

import com.app.Trading.platform.model.TwoFactorOTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwoFactorOTPRepository extends JpaRepository<TwoFactorOTP, Long> {
    TwoFactorOTP findUserById(String userId);
}
