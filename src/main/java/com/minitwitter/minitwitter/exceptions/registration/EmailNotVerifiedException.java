package com.minitwitter.minitwitter.exceptions.registration;


import org.springframework.security.core.AuthenticationException;

public class EmailNotVerifiedException extends AuthenticationException {
    public EmailNotVerifiedException(String explanation) {
        super(explanation);
    }

    public EmailNotVerifiedException(String msg, Throwable cause) {
        super(msg);
    }
}
