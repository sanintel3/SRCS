package com.srcs.acknowledgement.rest;

import com.srcs.acknowledgement.domain.EmailStatus;
import com.srcs.acknowledgement.rest.api.AcknowledgementStatus;
import org.springframework.stereotype.Component;

/**
 * Created by santhosh on 23/07/16.
 */
@Component
public class ResponseMapper {

    public AcknowledgementStatus map(EmailStatus emailStatus){
        AcknowledgementStatus status = new AcknowledgementStatus();
        status.setEmail(emailStatus.getEmail().value());
        status.setHasMailSent(emailStatus.isSuccess());
        if(!emailStatus.isSuccess()){
            status.setErrorMessage(emailStatus.getErrorMessage());
        }
        return status;
    }
}
