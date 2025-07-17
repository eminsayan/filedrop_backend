package com.filedrop.backend.controller.mapper;

import com.filedrop.backend.controller.resource.FileResource;
import com.filedrop.backend.model.File;
import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring")
public interface FileMapper {
    FileResource toResource(File file);
}
