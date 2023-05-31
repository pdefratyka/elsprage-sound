package com.elsprage.sound.service.impl;

import com.elsprage.sound.kafka.producer.WordModificationEventProducer;
import com.elsprage.sound.service.AudioService;
import com.elsprage.sound.service.WordAudioProcessorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class WordAudioProcessorServiceImpl implements WordAudioProcessorService {

    private final AudioService audioService;
    private final WordModificationEventProducer wordModificationEventProducer;

    @Override
    public void processUpdatingWordAudio(String key, String language, Long wordId) {
        final byte[] audio = audioService.getAudio(key, language, wordId);
        wordModificationEventProducer.sendMessage(wordId, audio);
    }
}
