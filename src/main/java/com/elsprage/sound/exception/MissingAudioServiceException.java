package com.elsprage.sound.exception;

public class MissingAudioServiceException extends RuntimeException {

    public MissingAudioServiceException(String language) {
        super("No audio service for language: " + language);
    }
}
