package com.zealep.ventasbackend.exception;

public class FileException extends RuntimeException {
    private static final String DESCRIPTION = "File exception";

    public FileException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
