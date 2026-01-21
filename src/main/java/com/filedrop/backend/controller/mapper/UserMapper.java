package com.filedrop.backend.controller.mapper;

import com.filedrop.backend.controller.dto.UserDto;
import com.filedrop.backend.model.User;
import com.filedrop.backend.controller.resource.UserResource;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto dto);

    UserResource toResource(User user);

    List<UserResource> toResourceList(List<User> users);
}
