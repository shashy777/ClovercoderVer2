package com.clove.clovewerable;

import java.io.Serializable;

/**
 * @author unascribed
 */
public class User implements Serializable {

    private static final long serialVersionUID = -7788619177798333712L;

    private String loginId;
    private String name;
    private String email;
    private String pincode;
    private String regToken;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRegToken() {
        return regToken;
    }

    public void setRegToken(String regToken) {
        this.regToken = regToken;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("USER ID = ").append(loginId).append(", ");
        sb.append("NAME = ").append(name).append(", ");
        sb.append("EMAIL = ").append(email).append(", ");
        sb.append("PIN = ").append(pincode).append(", ");
        sb.append("MSG = ").append(message);

        return sb.toString();
    }
}
