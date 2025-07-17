package com.filedrop.backend.service;

import com.filedrop.backend.model.File;
import com.filedrop.backend.model.User;
import com.filedrop.backend.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    private final Path root = Paths.get("uploads");

    public File saveFile(MultipartFile file, User user) {

        if (!file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
            throw new RuntimeException("Sadece PDF dosyalar yüklenebilir!");
        }

        try {

            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path filePath = this.root.resolve(filename);
            Files.copy(file.getInputStream(), filePath);

            File fileEntity = new File();
            fileEntity.setFilename(file.getOriginalFilename());
            fileEntity.setFilePath(filePath.toString());
            fileEntity.setUploadedAt(LocalDateTime.now());
            fileEntity.setUser(user);

            fileRepository.save(fileEntity);

            return fileEntity;
        } catch (Exception e) {
            throw new RuntimeException("Dosya kaydedilemedi: " + e.getMessage());
        }

    }

    public List<File> getUserFiles(User user) {
        return fileRepository.findByUser(user);
    }
}