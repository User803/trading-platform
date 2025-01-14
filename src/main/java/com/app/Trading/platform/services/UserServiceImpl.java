package com.app.Trading.platform.services;

import com.app.Trading.platform.config.JwtProvider;
import com.app.Trading.platform.domain.VerificationType;
import com.app.Trading.platform.model.TwoFactorAuth;
import com.app.Trading.platform.model.User;
import com.app.Trading.platform.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserProfileByJwt(String jwt) {
        String email = JwtProvider.getEmailFromToken(jwt);
        Optional<User> user = userRepository.findUserByEmail(email);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return user.get();
    }

    @Override
    public User findUserByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return user.get();
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return user.get();
    }

    @Override
    public User enableTwoFactorAuth(VerificationType verificationType, String sendTo, User user) {
        TwoFactorAuth twoFactorAuth = new TwoFactorAuth();
        twoFactorAuth.setEnabled(true);
        twoFactorAuth.setSendTo(verificationType);

        user.setTwoFactorAuth(twoFactorAuth);

        return userRepository.save(user);
    }

    @Override
    public User updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        return userRepository.save(user);
    }
}
