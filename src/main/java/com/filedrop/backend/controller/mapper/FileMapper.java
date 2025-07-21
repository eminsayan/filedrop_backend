package com.filedrop.backend.controller.mapper;

import com.filedrop.backend.controller.resource.FileResource;
import com.filedrop.backend.model.File;
import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileMapper {
    FileResource toResource(File file);

    List<FileResource> toResourceList(List<File> files);
}
