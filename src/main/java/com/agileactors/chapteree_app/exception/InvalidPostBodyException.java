package com.agileactors.chapteree_app.exception;

import org.springframework.dao.DataAccessException;

public class InvalidPostBodyException extends DataAccessException {
    public InvalidPostBodyException(String msg) {
        super(msg);
    }
}
