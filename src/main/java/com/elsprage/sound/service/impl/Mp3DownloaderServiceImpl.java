package com.elsprage.sound.service.impl;

import com.elsprage.sound.exception.Mp3ProcessingException;
import com.elsprage.sound.service.Mp3DownloaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.net.URL;
import java.net.URLConnection;

@Service
@Slf4j
public class Mp3DownloaderServiceImpl implements Mp3DownloaderService {
    @Override
    public byte[] downloadMp3File(String url) {
        log.info("Downloading mp3 file from url: {}", url);
        try {
            URL audioUrl = new URL(url);
            URLConnection connection = audioUrl.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");
            return FileCopyUtils.copyToByteArray(connection.getInputStream());
        } catch (Exception ex) {
            throw new Mp3ProcessingException(ex);
        }
    }
}
