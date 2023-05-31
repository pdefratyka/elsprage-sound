package com.elsprage.sound.exception;

public class AudioUrlNotFoundException extends RuntimeException {

    public AudioUrlNotFoundException() {
        super("Audio url not found");
    }
}
