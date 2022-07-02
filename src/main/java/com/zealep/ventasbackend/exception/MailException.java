package com.zealep.ventasbackend.exception;

public class MailException extends com.zealep.ventasbackend.exception.ConflictException {
    private static final String DESCRIPTION = "Mail exception";

    public MailException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}