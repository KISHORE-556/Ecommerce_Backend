package dev.kishore.authservice.dtos;

import dev.kishore.authservice.Model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class userDto {
    private String email;
    private Set<Role> roles;
}
