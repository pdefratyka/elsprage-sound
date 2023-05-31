package com.elsprage.sound.service.impl;

import com.elsprage.sound.exception.AudioUrlNotFoundException;
import com.elsprage.sound.exception.MissingAudioServiceException;
import com.elsprage.sound.service.AudioService;
import com.elsprage.sound.service.Mp3DownloaderService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AudioServiceImpl implements AudioService {

    private final EnglishAudioServiceImpl englishAudioService;
    private final Mp3DownloaderService mp3DownloaderService;

    @Override
    public byte[] getAudio(String key, String language, Long wordId) {
        String url;
        if (language.equals("en")) {
            url = englishAudioService.getAudioUrl(key);
        } else {
            throw new MissingAudioServiceException(language);
        }
        if (StringUtils.isEmpty(url)) {
            throw new AudioUrlNotFoundException();
        }
        return mp3DownloaderService.downloadMp3File(url);
    }
}
