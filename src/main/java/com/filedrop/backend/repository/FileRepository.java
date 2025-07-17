package com.filedrop.backend.repository;

import com.filedrop.backend.model.File;
import com.filedrop.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByUser(User user);  // sadece o kullanıcıya ait dosyalar gelsin
}