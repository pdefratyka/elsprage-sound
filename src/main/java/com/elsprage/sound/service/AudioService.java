package com.elsprage.sound.service;

public interface AudioService {
    byte[] getAudio(String key, String language, Long wordId);
}
