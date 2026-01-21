package com.filedrop.backend.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder; // SuperBuilder'ı import etmeyi unutmayın

import java.time.LocalDateTime; // Bu artık BaseEntity'de
import java.util.UUID; // Bu da artık BaseEntity'de

@Entity
@Table(name = "FILES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Media extends BaseEntity {

    private String mediaName;
    private String mediaPath;
    private long mediaSize;

}