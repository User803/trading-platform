package com.app.Trading.platform.controllers;

import com.app.Trading.platform.config.JwtProvider;
import com.app.Trading.platform.model.TwoFactorOTP;
import com.app.Trading.platform.model.User;
import com.app.Trading.platform.repositories.UserRepository;
import com.app.Trading.platform.response.AuthResponse;
import com.app.Trading.platform.services.CustomUserDetailsService;
import com.app.Trading.platform.services.EmailService;
import com.app.Trading.platform.services.TwoFactorOTPService;
import com.app.Trading.platform.services.WatchListService;
import com.app.Trading.platform.utils.OTPUtils;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final TwoFactorOTPService twoFactorOTPService;
    private final EmailService emailService;
    private final WatchListService watchListService;

    public AuthController(UserRepository userRepository, CustomUserDetailsService customUserDetailsService, TwoFactorOTPService twoFactorOTPService, EmailService emailService, WatchListService watchListService) {
        this.userRepository = userRepository;
        this.customUserDetailsService = customUserDetailsService;
        this.twoFactorOTPService = twoFactorOTPService;
        this.emailService = emailService;
        this.watchListService = watchListService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findUserByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("E-mail exists with another account");
        }

        User newUser = new User();
        newUser.setFullName(user.getFullName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        User savedUser = userRepository.save(newUser);

        watchListService.createWatchList(savedUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()
        );

        String jwt = JwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setStatus(true);
        authResponse.setMessage("Registration successful");

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) throws MessagingException {
        String username = user.getEmail();
        String password = user.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JwtProvider.generateToken(authentication);

        User existingUser = userRepository.findUserByEmail(username).orElse(null);

        // if 2FA is enabled
        if (user.getTwoFactorAuth().isEnabled()){
            AuthResponse authResponse = new AuthResponse();
            authResponse.setMessage("Two factor authentication is enabled");
            authResponse.setTwoFactorEnabled(true);
            String otp = OTPUtils.generateOTP();

            TwoFactorOTP oldTwoFactorOTP = twoFactorOTPService.findById(existingUser.getId());
            if (oldTwoFactorOTP != null) {
                twoFactorOTPService.deleteTwoFactorOTP(oldTwoFactorOTP);
            }

            TwoFactorOTP newTwoFactorOTP = twoFactorOTPService.createTwoFactorOTP(
                    existingUser, otp, jwt);

            emailService.sendVerificationOtpEmail(username, otp);

            authResponse.setSession(newTwoFactorOTP.getId());
            return new ResponseEntity<>(authResponse, HttpStatus.ACCEPTED);
        }

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setStatus(true);
        authResponse.setMessage("Login success");

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid email");
        }
        if (!password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,
                password,
                userDetails.getAuthorities());
    }

    @PostMapping("/two-factor/otp/{otp}")
    public ResponseEntity<AuthResponse> verifyLoginOtp(@PathVariable String otp,
                                                       @RequestParam String id) {
        TwoFactorOTP twoFactorOTP = twoFactorOTPService.findUserById(id);

        if (twoFactorOTPService.verifyTwoFactorOTP(twoFactorOTP, otp)) {
            AuthResponse authResponse = new AuthResponse();
            authResponse.setMessage("Two factor authentication verified");
            authResponse.setTwoFactorEnabled(true);
            authResponse.setJwt(twoFactorOTP.getJwt());
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        }

        throw new RuntimeException("Invalid otp");
    }
}