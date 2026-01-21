package com.filedrop.backend.controller.mapper;

import com.filedrop.backend.controller.resource.MediaResource;
import com.filedrop.backend.model.Media;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface MediaMapper {
    MediaResource toResource(Media file);

    List<MediaResource> toResourceList(List<Media> files);
}
