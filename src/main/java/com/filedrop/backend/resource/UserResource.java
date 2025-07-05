package com.filedrop.backend.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserResource {

    private UUID id;
    private String username;
}
