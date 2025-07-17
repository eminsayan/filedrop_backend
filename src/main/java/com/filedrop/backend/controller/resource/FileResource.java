package com.filedrop.backend.controller.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

// Todo (MK): Setter gerekmiyor mu burada? Çalışıyor mu bu?

@Getter
@AllArgsConstructor
public class FileResource {
    private UUID id;
    private String filename;
    private String filePath;
    private LocalDateTime uploadedAt;
}
