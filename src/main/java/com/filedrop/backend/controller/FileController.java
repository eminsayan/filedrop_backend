package com.filedrop.backend.controller;

import com.filedrop.backend.controller.mapper.FileMapper;
import com.filedrop.backend.controller.resource.FileResource;
import com.filedrop.backend.model.File;
import com.filedrop.backend.model.User;
import com.filedrop.backend.service.FileService;
import com.filedrop.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final UserService userService;
    private final FileMapper fileMapper;

    @PostMapping("/upload")
    public FileResource uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") UUID userId) {

        User user = userService.getUserById(userId);

        File savedFİle = fileService.saveFile(file, user);
        return fileMapper.toResource(savedFİle);

    }

    @GetMapping("/my-files")
    public List<FileResource> getMyFiles(@RequestParam("userId") UUID userId) {
        User user = userService.getUserById(userId);
        List<File> files = fileService.getUserFiles(user);

        return files.stream()
                .map(fileMapper::toResource)
                .collect(Collectors.toList());
    }
}
