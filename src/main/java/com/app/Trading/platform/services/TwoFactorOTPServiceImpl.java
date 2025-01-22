package com.app.Trading.platform.services;

import com.app.Trading.platform.model.TwoFactorOTP;
import com.app.Trading.platform.model.User;
import com.app.Trading.platform.repositories.TwoFactorOTPRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class TwoFactorOTPServiceImpl implements TwoFactorOTPService{
    private final TwoFactorOTPRepository twoFactorOTPRepository;

    public TwoFactorOTPServiceImpl(TwoFactorOTPRepository twoFactorOTPRepository) {
        this.twoFactorOTPRepository = twoFactorOTPRepository;
    }

    @Override
    public TwoFactorOTP createTwoFactorOTP(User user, String otp, String jwt) {
        String id = UUID.randomUUID().toString();

        TwoFactorOTP twoFactorOTP = new TwoFactorOTP(
                id, otp, jwt, user
        );

        return twoFactorOTPRepository.save(twoFactorOTP);
    }

    @Override
    public TwoFactorOTP findUserById(String userId) {
        return twoFactorOTPRepository.findUserById(userId);

    }

    @Override
    public TwoFactorOTP findById(Long userId) {
        return twoFactorOTPRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public boolean verifyTwoFactorOTP(TwoFactorOTP twoFactorOTP, String otp) {
        return twoFactorOTP.getOtp().equals(otp);
    }

    @Override
    public void deleteTwoFactorOTP(TwoFactorOTP twoFactorOTP) {
        twoFactorOTPRepository.delete(twoFactorOTP);
    }
}
