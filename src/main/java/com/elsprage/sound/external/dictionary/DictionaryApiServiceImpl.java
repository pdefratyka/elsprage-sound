package com.elsprage.sound.external.dictionary;

import com.elsprage.sound.external.WebClientUtils;
import com.elsprage.sound.model.dto.dictionary.DictionaryEntryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;
import java.util.List;

@Service
@Log4j2
public class DictionaryApiServiceImpl implements DictionaryApiService {

    private static final int TIMEOUT = 30000;
    private static final String BASE_URL = "/entries/en/";
    private final WebClient webClient;

    public DictionaryApiServiceImpl(final DictionaryApiProperties imageApiProperties, final ClientHttpConnector clientHttpConnector) {
        final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
        this.webClient = WebClientUtils.createWebClientBuilder(imageApiProperties, log, clientHttpConnector,
                objectMapper).build();
    }

    @Override
    public List<DictionaryEntryDto> getDictionaryApiResponse(String key) {
        final WebClient.RequestHeadersSpec<?> requestHeadersSpec = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(BASE_URL + key)
                        .build())
                .accept(MediaType.APPLICATION_JSON);
        try {
            return requestHeadersSpec.retrieve().bodyToMono(new ParameterizedTypeReference<List<DictionaryEntryDto>>() {
            }).timeout(Duration.ofMillis(TIMEOUT)).block();
        } catch (WebClientResponseException.NotFound e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
