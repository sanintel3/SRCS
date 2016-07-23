package com.srcs.acknowledgement.rest.api;

import com.fasterxml.classmate.AnnotationInclusion;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by santhosh on 23/07/16.
 */
@JsonSerialize
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AcknowledgementStatus {

    private String email;
    private boolean hasMailSent;
    private String errorMessage;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isHasMailSent() {
        return hasMailSent;
    }

    public void setHasMailSent(boolean hasMailSent) {
        this.hasMailSent = hasMailSent;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
