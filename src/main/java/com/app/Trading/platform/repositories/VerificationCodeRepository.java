package com.app.Trading.platform.repositories;

import com.app.Trading.platform.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    VerificationCode findUserById(Long userId);
}
