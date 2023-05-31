package com.elsprage.sound.service.impl;

import com.elsprage.sound.service.AudioService;
import com.elsprage.sound.service.Mp3DownloaderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class AudioServiceImpl implements AudioService {

    private final EnglishAudioServiceImpl englishAudioService;
    private final Mp3DownloaderService mp3DownloaderService;

    @Override
    public byte[] getAudio(String key, String language, Long wordId) {
        String url;
        if (language.equals("en")) {
            url = englishAudioService.getAudioUrl(key);
        } else {
            log.error("There is no audio service implementation for language: " + language);
            return null;
        }
        if (StringUtils.isEmpty(url)) {
            log.error("Audio url not found");
            return null;
        }
        return mp3DownloaderService.downloadMp3File(url);
    }
}
