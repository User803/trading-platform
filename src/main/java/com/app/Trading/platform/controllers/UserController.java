package com.app.Trading.platform.controllers;

import com.app.Trading.platform.request.ForgotPasswordTokenRequest;
import com.app.Trading.platform.domain.VerificationType;
import com.app.Trading.platform.model.ForgotPasswordToken;
import com.app.Trading.platform.model.User;
import com.app.Trading.platform.model.VerificationCode;
import com.app.Trading.platform.request.ResetPasswordRequest;
import com.app.Trading.platform.response.ApiResponse;
import com.app.Trading.platform.response.AuthResponse;
import com.app.Trading.platform.services.EmailService;
import com.app.Trading.platform.services.ForgotPasswordService;
import com.app.Trading.platform.services.UserService;
import com.app.Trading.platform.services.VerificationCodeService;
import com.app.Trading.platform.utils.OTPUtils;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final EmailService emailService;
    private final VerificationCodeService verificationCodeService;
    private final ForgotPasswordService forgotPasswordService;

    public UserController(UserService userService, EmailService emailService, VerificationCodeService verificationCodeService, ForgotPasswordService forgotPasswordService) {
        this.userService = userService;
        this.emailService = emailService;
        this.verificationCodeService = verificationCodeService;
        this.forgotPasswordService = forgotPasswordService;
    }

    @GetMapping("/users/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserProfileByJwt(jwt);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users/verification/{verificationType}/send-otp")
    public ResponseEntity<String> sendVerificationOtp(@RequestHeader("Authorization") String jwt,
                                                    @PathVariable VerificationType verificationType) throws MessagingException {
        User user = userService.findUserProfileByJwt(jwt);

        VerificationCode verificationCode =
                verificationCodeService.getVerificationCodeByUser(user.getId());

        if (verificationCode == null) {
            verificationCode = verificationCodeService.sendVerificationCode(user, verificationType);
        }

        if (verificationType.equals(VerificationType.EMAIL)) {
            emailService.sendVerificationOtpEmail(user.getEmail(), verificationCode.getOtp());
        }

        return new ResponseEntity<>("Verification code sent", HttpStatus.OK);
    }

    @PostMapping("/auth/users/reset-password/send-otp")
    public ResponseEntity<AuthResponse> sendForgotPasswordOtp(
            @RequestBody ForgotPasswordTokenRequest forgotPasswordTokenRequest) throws MessagingException {

      User user = userService.findUserByEmail(forgotPasswordTokenRequest.getSendTo());
      String otp = OTPUtils.generateOTP();
      String id = UUID.randomUUID().toString();

      ForgotPasswordToken forgotPasswordToken =
                forgotPasswordService.findByUser(user.getId());

        if (forgotPasswordToken == null) {
            forgotPasswordToken = forgotPasswordService.createToken(
                    user,
                    id,
                    otp,
                    forgotPasswordTokenRequest.getVerificationType(),
                    forgotPasswordTokenRequest.getSendTo()
            );
        }

        if (forgotPasswordTokenRequest.getVerificationType().equals(VerificationType.EMAIL)) {
            emailService.sendVerificationOtpEmail(
                    user.getEmail(),
                    forgotPasswordToken.getOtp());
        }

        AuthResponse authResponse = new AuthResponse();
        authResponse.setSession(forgotPasswordToken.getId());
        authResponse.setMessage("Password reset OTP sent successfully");

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PatchMapping("/users/enable2FA/verify-otp/{otp}")
    public ResponseEntity<User> enableTwoFactorAuth(
            @PathVariable String otp,
            @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserProfileByJwt(jwt);

        VerificationCode verificationCode = verificationCodeService.getVerificationCodeByUser(user.getId());
        String sendTo = verificationCode.getVerificationType().equals(VerificationType.EMAIL) ?
                verificationCode.getEmail() :
                verificationCode.getMobile();

        boolean isVerified = verificationCode.getOtp().equals(otp);

        if (isVerified) {
            User updatedUser = userService.enableTwoFactorAuth(
                    verificationCode.getVerificationType(),
                    sendTo,
                    user);
            verificationCodeService.deleteVerificationCode(verificationCode);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }

        throw new RuntimeException("Wrong Otp");
    }

    @PatchMapping("/auth/users/reset-password/verify-otp")
    public ResponseEntity<ApiResponse> resetPassword(
            @RequestParam String id,
            @RequestBody ResetPasswordRequest resetPasswordRequest,
            @RequestHeader("Authorization") String jwt) {

        ForgotPasswordToken forgotPasswordToken = forgotPasswordService.findById(id);

        boolean isVerified = forgotPasswordToken.getOtp().equals(resetPasswordRequest.getOtp());

        if (isVerified) {
            userService.updatePassword(forgotPasswordToken.getUser(), resetPasswordRequest.getPassword());
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("password update successfully");
            return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
        }

        throw new RuntimeException("wrong otp");
    }

}
