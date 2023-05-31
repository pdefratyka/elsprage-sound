package com.elsprage.sound.external.dictionary;


import com.elsprage.sound.common.constants.Constants;
import com.elsprage.sound.external.WebClientProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = DictionaryApiProperties.PREFIX)
@Data
public class DictionaryApiProperties extends WebClientProperties {
    public static final String PREFIX = Constants.ELSPRAGE_PREFIX + ".dictionary.api";
}
