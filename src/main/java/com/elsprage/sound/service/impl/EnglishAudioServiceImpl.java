package com.elsprage.sound.service.impl;

import com.elsprage.sound.exception.AudioUrlNotFoundException;
import com.elsprage.sound.external.dictionary.DictionaryApiService;
import com.elsprage.sound.model.dto.dictionary.DictionaryEntryDto;
import com.elsprage.sound.model.dto.dictionary.PhoneticDto;
import com.elsprage.sound.service.AudioUrlService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EnglishAudioServiceImpl implements AudioUrlService {

    private final DictionaryApiService dictionaryApiService;

    @Override
    public String getAudioUrl(String key) {
        final List<DictionaryEntryDto> dictionaryEntries = dictionaryApiService.getDictionaryApiResponse(key);
        final Optional<String> audioUrl = dictionaryEntries.stream()
                .flatMap(dictionaryEntryDto -> dictionaryEntryDto.phonetics().stream())
                .map(PhoneticDto::audio)
                .filter(audio -> audio.contains("-us.mp3"))
                .findFirst();
        return audioUrl.orElseGet(() -> dictionaryEntries.stream()
                .flatMap(dictionaryEntryDto -> dictionaryEntryDto.phonetics().stream())
                .map(PhoneticDto::audio)
                .findFirst().orElseThrow(AudioUrlNotFoundException::new));
    }
}
