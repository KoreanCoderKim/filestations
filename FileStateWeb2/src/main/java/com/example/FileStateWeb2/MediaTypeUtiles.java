package com.example.FileStateWeb2;

import jakarta.servlet.ServletContext;
import org.springframework.http.MediaType;

public class MediaTypeUtiles {
    public static MediaType getMediaTypeForFileName(ServletContext sc, String filename) {
        String mimType = sc.getMimeType(filename);
        try {
            MediaType mediaType = MediaType.parseMediaType(mimType);

            return mediaType;
        }catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}
