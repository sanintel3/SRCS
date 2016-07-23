package com.srcs.datatypes;

/**
 * Created by santhosh on 09/07/16.
 */
public class Email extends AbstractStringType {

    private Email(String value) {
        super(value);
    }

    public static Email newEmail(String value){
        return new Email(value);
    }
}
