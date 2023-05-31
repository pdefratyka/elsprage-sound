package com.elsprage.sound.model.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WordModificationMessage {
    private Long wordId;
    private byte[] audioFile;
    private String eventType;
}
