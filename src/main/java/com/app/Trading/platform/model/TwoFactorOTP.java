package com.app.Trading.platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class TwoFactorOTP {
    @Id
    private String id;
    private String otp;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String jwt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    private User user;

    public TwoFactorOTP() { }

    public TwoFactorOTP(String id, String otp, String jwt, User user) {
        this.id = id;
        this.otp = otp;
        this.jwt = jwt;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
