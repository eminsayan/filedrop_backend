package com.filedrop.backend.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Nullable
    private UUID createdBy;
    @Nullable
    private LocalDateTime createdDate;

    @Nullable
    private UUID lastModifiedBy;

    @Nullable
    private LocalDateTime lastModifiedDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
        // createdBy alanını ayarlamak için burada bir UserContext veya SecurityContext kullanmanız gerekebilir.
        // Şimdilik null bırakıyorum veya sabit bir değer atıyorum, gerçek uygulamada güvenlik bağlamından alınır.
        // this.createdBy = getCurrentUserId(); // Örnek: Gerçek bir uygulamada geçerli kullanıcının ID'si alınır
        // this.lastModifiedBy = getCurrentUserId(); // Örnek
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedDate = LocalDateTime.now();
        // lastModifiedBy alanını ayarlamak için burada bir UserContext veya SecurityContext kullanmanız gerekebilir.
        // this.lastModifiedBy = getCurrentUserId(); // Örnek
    }
}
