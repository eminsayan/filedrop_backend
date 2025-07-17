package com.filedrop.backend.controller.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class FileResource {
    private UUID id;
    private String filename;
    private String filePath;
    private LocalDateTime uploadedAt;
}