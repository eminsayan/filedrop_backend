package com.filedrop.backend.controller.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaResource {
    private UUID id;

    private String mediaName;

    private String mediaPath;

    private LocalDateTime createdDate;

    private UUID createdBy;

}
