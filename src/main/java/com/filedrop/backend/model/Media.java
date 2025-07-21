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
public class Media {
    /*
    Todo (MK): Ayrıca şu alanları BaseEntity adında bir class'ta toplayalım ve diğer entity'ler ondan
    miras alsın (böylece kod tekrarı azalır biraz):
    id
    createdBy (username)
    createdDate (LocalDateTime)
    lastModifiedBy (username)
    lastModifiedDate (LocalDateTime)
    ipucu: @MappedSuperclass
     */

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String medianame;         // Orijinal dosya adı
    private String mediaPath;         // Kaydedildiği yer
    private LocalDateTime uploadedAt;

    @ManyToOne
    private User user;               // Hangi kullanıcı yükledi
    // Todo (MK): user alanını kaldıralım. bu alan zaten BaseEntity'de tutulacak, ayrıca

}
