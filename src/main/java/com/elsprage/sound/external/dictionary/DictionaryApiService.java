package com.elsprage.sound.external.dictionary;

import com.elsprage.sound.model.dto.dictionary.DictionaryEntryDto;

import java.util.List;

public interface DictionaryApiService {
    List<DictionaryEntryDto> getDictionaryApiResponse(String key);
}
