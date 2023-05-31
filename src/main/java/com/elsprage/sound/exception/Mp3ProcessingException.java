package com.elsprage.sound.exception;

public class Mp3ProcessingException extends RuntimeException {
    public Mp3ProcessingException(Throwable ex) {
        super("Error during processing mp3", ex);
    }
}
