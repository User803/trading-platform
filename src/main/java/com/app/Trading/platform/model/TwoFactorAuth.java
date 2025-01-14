package com.app.Trading.platform.model;

import com.app.Trading.platform.domain.VerificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class TwoFactorAuth {

    private boolean isEnabled = false;
    private VerificationType sendTo;

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public VerificationType getSendTo() {
        return sendTo;
    }

    public void setSendTo(VerificationType sendTo) {
        this.sendTo = sendTo;
    }

    @Override
    public String toString() {
        return "TwoFactorAuth{" +
                "isEnabled=" + isEnabled +
                ", sendTo=" + sendTo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TwoFactorAuth that = (TwoFactorAuth) o;
        return isEnabled == that.isEnabled && sendTo == that.sendTo;
    }

    @Override
    public int hashCode() {
        int result = Boolean.hashCode(isEnabled);
        result = 31 * result + Objects.hashCode(sendTo);
        return result;
    }
}
