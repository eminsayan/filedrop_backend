package com.filedrop.backend.controller;

import com.filedrop.backend.controller.mapper.FileMapper;
import com.filedrop.backend.controller.resource.FileResource;
import com.filedrop.backend.model.File;
import com.filedrop.backend.model.User;
import com.filedrop.backend.service.FileService;
import com.filedrop.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public FileResource uploadFile(@RequestParam("file") MultipartFile file,
                                   @RequestParam("userId") UUID userId) {

        User user = userService.getUserById(userId);

        File savedFile = fileService.saveFile(file, user);
        return fileMapper.toResource(savedFile);

    }

    // Todo (MK): my-files olmamalı (user-files veya user/{userId}/files tarzı bir şey daha doğru bir isimlendirme gibi geldi)
    @GetMapping("/my-files")
    public List<FileResource> getMyFiles(@RequestParam("userId") UUID userId) {
        User user = userService.getUserById(userId);
        List<File> files = fileService.getUserFiles(user); // Todo (MK): user'ı tamamen neden veriyoruz, bu metot sadece userId alsa yetmez mi?

        // Todo (MK): listeleri map'lemek için mapstruct'ın yöntemi olmalı, bu kullanım biraz tuhaf geldi
        return files.stream()
                .map(fileMapper::toResource)
                .collect(Collectors.toList());
    }
}
