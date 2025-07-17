package com.filedrop.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "FILES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String filename;         // Orijinal dosya adı
    private String filePath;         // Kaydedildiği yer
    private LocalDateTime uploadedAt;

    @ManyToOne
    private User user;               // Hangi kullanıcı yükledi

}