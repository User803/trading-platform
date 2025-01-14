package com.app.Trading.platform.services;

import com.app.Trading.platform.domain.VerificationType;
import com.app.Trading.platform.model.User;
import com.app.Trading.platform.model.VerificationCode;

public interface VerificationCodeService {

    VerificationCode sendVerificationCode(User user, VerificationType verificationType);
    VerificationCode getVerificationCodeById(Long id);
    VerificationCode getVerificationCodeByUser(Long id);
    void deleteVerificationCode(VerificationCode verificationCode);
}
