package com.projeto.pessoal.exceptions;

import java.io.Serial;

public class RequiredObjectsIsNullException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RequiredObjectsIsNullException() {
        super("It is not allowed to persist a null object");
    }

    public RequiredObjectsIsNullException(String msg) {
        super(msg);
    }
}
