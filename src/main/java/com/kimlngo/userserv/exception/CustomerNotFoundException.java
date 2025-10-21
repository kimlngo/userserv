package com.kimlngo.userserv.exception;

import java.io.Serial;

public class CustomerNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(Long id) {
        super("Could not find customer " + id);
    }
}
