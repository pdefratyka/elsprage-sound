package com.elsprage.sound.model.dto.dictionary;

import java.util.List;

public record DictionaryEntryDto(String word, List<PhoneticDto> phonetics) {
}
