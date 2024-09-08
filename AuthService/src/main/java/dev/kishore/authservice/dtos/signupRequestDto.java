package dev.kishore.authservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class signupRequestDto {

    private String email;
    private String password;
}
