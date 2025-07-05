package com.filedrop.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // Todo: UUID tipinde olsun id'ler

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

}
