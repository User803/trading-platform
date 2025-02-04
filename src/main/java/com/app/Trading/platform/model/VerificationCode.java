package com.app.Trading.platform.model;

import com.app.Trading.platform.domain.VerificationType;
import jakarta.persistence.*;

@Entity
public class VerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String otp;
    private String email;
    private String mobile;

    @Enumerated(EnumType.STRING)
    private VerificationType verificationType;

    @OneToOne
    private User user;

    public VerificationCode() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public VerificationType getVerificationType() {
        return verificationType;
    }

    public void setVerificationType(VerificationType verificationType) {
        this.verificationType = verificationType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
