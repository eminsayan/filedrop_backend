package com.filedrop.backend.controller;

import com.filedrop.backend.controller.mapper.MediaMapper;
import com.filedrop.backend.controller.resource.MediaResource;
import com.filedrop.backend.model.Media;
import com.filedrop.backend.model.User;
import com.filedrop.backend.service.MediaService;
import com.filedrop.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;
    private final UserService userService;
    private final MediaMapper mediaMapper;

    @PostMapping("/upload")
    public MediaResource uploadMedia(@RequestParam("file") MultipartFile media,
                                     @RequestParam("userId") UUID userId) {

        User user = userService.getUserById(userId);

        Media savedMedia = mediaService.saveMedia(media, user);
        return mediaMapper.toResource(savedMedia);

    }

    @GetMapping("/user-files")
    public List<MediaResource> getMyMedias(@RequestParam("userId") UUID userId) {
        List<Media> files = mediaService.getUserMedias(userId);

        return mediaMapper.toResourceList(files);
    }
}
