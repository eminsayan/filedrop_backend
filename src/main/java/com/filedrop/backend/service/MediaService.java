package com.filedrop.backend.service;

import com.filedrop.backend.model.Media;
import com.filedrop.backend.model.User;
import com.filedrop.backend.repository.MediaRepository;
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
public class MediaService {

    private final MediaRepository fileRepository;

    // Todo (MK): burada root değerini yml dosyasından alalım (@Value)
    private final Path root = Paths.get("uploads");

    public Media saveMedia(MultipartFile media, User user) {

        // Todo (MK): mimeType ile kontrol etmek daha doğru olabilir. Bunu bir araştırsana hangisi daha doğruymuş (deftere de yazarsın)
        if (!media.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
            throw new RuntimeException("Sadece PDF dosyalar yüklenebilir!");
        }

        try {

            String mediaName = UUID.randomUUID() + "_" + media.getOriginalFilename();

            Path mediaPath = this.root.resolve(mediaName);
            Files.copy(media.getInputStream(), mediaPath);

            Media mediaEntity = new Media();
            mediaEntity.setMediaName(media.getOriginalFilename());
            mediaEntity.setMediaPath(mediaPath.toString());
            mediaEntity.setCreatedDate(LocalDateTime.now());
            mediaEntity.setCreatedBy(user.getId());
            mediaEntity.setLastModifiedDate(LocalDateTime.now());
            mediaEntity.setLastModifiedBy(user.getId());

            // Todo (MK): Bu tarz alanların setlenmesi için @PrePersist ve @PreUpdate gibi anotasyonlar kullanılıyor olmalı,
            //  bir araştırıp kullanmayı dener misin? Ama önce File entity'sine yazdığım nota bak.


            fileRepository.save(mediaEntity);

            return mediaEntity;
        } catch (Exception e) {
            throw new RuntimeException("Dosya kaydedilemedi: " + e.getMessage());
        }

    }

    public List<Media> getUserMedias(UUID userId) {
        return fileRepository.findByCreatedBy(userId);
    }
}
