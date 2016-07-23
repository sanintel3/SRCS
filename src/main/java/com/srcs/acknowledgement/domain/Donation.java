package com.srcs.acknowledgement.domain;

import com.srcs.datatypes.Email;
import com.srcs.datatypes.Money;

import java.util.Date;

/**
 * Created by santhosh on 09/07/16.
 */
public class Donation {

    private Email email;
    private Money amount;
    private Date date;

    public Donation(Email email, Money amount, Date date) {
        this.email = email;
        this.amount = amount;
        this.date = date;
    }

    public Email email(){
        return email;
    }

    public Money amount(){
        return amount;
    }

    public Date date(){
        return date;
    }


}
