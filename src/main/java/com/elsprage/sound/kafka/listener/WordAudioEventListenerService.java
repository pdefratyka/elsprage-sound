package com.elsprage.sound.kafka.listener;

import com.elsprage.external.words.avro.WordAudioEvent;
import com.elsprage.sound.kafka.config.KafkaConstants;
import com.elsprage.sound.service.WordAudioProcessorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class WordAudioEventListenerService {

    private final WordAudioProcessorService wordAudioProcessorService;

    @KafkaListener(topics = KafkaConstants.AUDIO_TOPIC, groupId = KafkaConstants.GROUP_ID)
    public void consumeMessage(WordAudioEvent message) {
        log.info("Received message: {}", message);
        wordAudioProcessorService.processUpdatingWordAudio(message.getKey().toString(), message.getLanguage().toString(), message.getWordId());
    }
}
