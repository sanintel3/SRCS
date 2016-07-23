package com.srcs.acknowledgement.domain;

import com.srcs.datatypes.Email;

/**
 * Created by santhosh on 23/07/16.
 */
public class EmailStatus {

    private final Email email;
    private final boolean isSuccess;
    private final String errorMessage;

    public EmailStatus(Email email, boolean isSuccess, String errorMessage) {
        this.email = email;
        this.isSuccess = isSuccess;
        this.errorMessage = errorMessage;
    }

    public Email getEmail() {
        return email;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
