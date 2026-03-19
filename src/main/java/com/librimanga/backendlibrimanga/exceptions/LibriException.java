package com.librimanga.backendlibrimanga.exceptions;

public class LibriException extends RuntimeException {
    public LibriException() {
        super();
    }

    public LibriException(String message) {
        super(message);
    }
}
