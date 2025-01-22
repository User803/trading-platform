package com.app.Trading.platform.services;

import com.app.Trading.platform.domain.VerificationType;
import com.app.Trading.platform.model.User;
import com.app.Trading.platform.model.VerificationCode;
import com.app.Trading.platform.repositories.VerificationCodeRepository;
import com.app.Trading.platform.utils.OTPUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class VerificationCodeServiceImpl implements VerificationCodeService{

    private final VerificationCodeRepository verificationCodeRepository;

    public VerificationCodeServiceImpl(VerificationCodeRepository verificationCodeRepository) {
        this.verificationCodeRepository = verificationCodeRepository;
    }

    @Override
    public VerificationCode sendVerificationCode(User user, VerificationType verificationType) {
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(OTPUtils.generateOTP());
        verificationCode.setVerificationType(verificationType);
        verificationCode.setUser(user);

        return verificationCodeRepository.save(verificationCode);
    }

    @Override
    public VerificationCode getVerificationCodeById(Long id) {
        Optional<VerificationCode> verificationCode =
                verificationCodeRepository.findById(id);

        if (verificationCode.isPresent()) {
            return verificationCode.get();
        }

        throw new RuntimeException("Verification code not found");
    }

    @Override
    public VerificationCode getVerificationCodeByUser(Long id) {
        return verificationCodeRepository.findUserById(id);
    }

    @Override
    public void deleteVerificationCode(VerificationCode verificationCode) {
        verificationCodeRepository.delete(verificationCode);
    }
}
