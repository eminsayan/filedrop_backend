package com.filedrop.backend.mapper;

import com.filedrop.backend.dto.UserDto;
import com.filedrop.backend.model.User;
import com.filedrop.backend.resource.UserResource;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto dto);

    UserResource toResource(User user);
}
