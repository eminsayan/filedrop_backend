package com.filedrop.backend.service;

import com.filedrop.backend.model.Media;
import com.filedrop.backend.model.User;
import com.filedrop.backend.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;

    private final Path root;

    public MediaService(MediaRepository mediaRepository,
                        @Value("${app.upload.directory}") String uploadDirectory) {
        this.mediaRepository = mediaRepository;
        this.root = Paths.get(uploadDirectory);
    }

    public Media saveMedia(MultipartFile media, User user) {

        String contentType = media.getContentType();

        if (contentType == null || (!contentType.equals("application/pdf") && !contentType.equals("application/x-pdf"))) {
            throw new RuntimeException("Sadece PDF dosyaları yüklenebilir! Geçersiz dosya tipi: " + contentType);
        }

        try {

            String mediaName = UUID.randomUUID() + "_" + media.getOriginalFilename();

            Path mediaPath = this.root.resolve(mediaName);
            Files.copy(media.getInputStream(), mediaPath);

            Media mediaEntity = new Media();
            mediaEntity.setMediaName(media.getOriginalFilename());
            mediaEntity.setMediaPath(mediaPath.toString());
            mediaEntity.setCreatedBy(user.getId());
            mediaEntity.setLastModifiedBy(user.getId());
            mediaEntity.setMediaSize(media.getSize());


            mediaRepository.save(mediaEntity);

            return mediaEntity;
        } catch (Exception e) {
            throw new RuntimeException("Dosya kaydedilemedi: " + e.getMessage());
        }

    }

    public List<Media> getUserMedias(UUID userId) {
        return mediaRepository.findByCreatedBy(userId);
    }
}
