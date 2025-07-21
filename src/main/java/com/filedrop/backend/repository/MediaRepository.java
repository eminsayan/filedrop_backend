package com.filedrop.backend.repository;

import com.filedrop.backend.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MediaRepository extends JpaRepository<Media, Long> {
    List<Media> findByUser_Id(UUID userId);
}