package com.filedrop.backend.service;

import com.filedrop.backend.model.Media;
import com.filedrop.backend.model.User;
import com.filedrop.backend.repository.MediaRepository;
import org.docx4j.convert.out.pdf.PdfConversion;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.convert.out.pdf.viaXSLFO.Conversion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
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

        try {
            if ("application/pdf".equals(contentType) || "application/x-pdf".equals(contentType)) {

                String mediaName = UUID.randomUUID() + "_" + media.getOriginalFilename();
                Path mediaPath = this.root.resolve(mediaName);
                Files.copy(media.getInputStream(), mediaPath);

                Media mediaEntity = new Media();
                mediaEntity.setMediaName(mediaName);
                mediaEntity.setMediaPath(mediaPath.toString());
                mediaEntity.setCreatedBy(user.getId());
                mediaEntity.setLastModifiedBy(user.getId());
                mediaEntity.setMediaSize(media.getSize());

                return mediaRepository.save(mediaEntity);

            } else if ("application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(contentType)) {

                String docxName = UUID.randomUUID() + "_" + media.getOriginalFilename();
                Path docxPath = this.root.resolve(docxName);
                Files.copy(media.getInputStream(), docxPath);
                
                String pdfName = docxName.replace(".docx", ".pdf");
                Path pdfPath = this.root.resolve(pdfName);

                WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(docxPath.toFile());

                PdfConversion conversion = new Conversion(wordMLPackage);

                try (OutputStream os = Files.newOutputStream(pdfPath)) {
                    conversion.output(os, new PdfSettings());
                }

                Files.deleteIfExists(docxPath);

                Media mediaEntity = new Media();
                mediaEntity.setMediaName(pdfName);
                mediaEntity.setMediaPath(pdfPath.toString());
                mediaEntity.setCreatedBy(user.getId());
                mediaEntity.setLastModifiedBy(user.getId());
                mediaEntity.setMediaSize(Files.size(pdfPath));

                return mediaRepository.save(mediaEntity);
            } else {
                throw new RuntimeException("Desteklenmeyen içerik türü: " + contentType);
            }

        } catch (Exception e) {
            throw new RuntimeException("Dosya kaydedilemedi: " + e.getMessage(), e);
        }
    }

    public List<Media> getUserMedias(UUID userId) {
        return mediaRepository.findByCreatedBy(userId);
    }
}
