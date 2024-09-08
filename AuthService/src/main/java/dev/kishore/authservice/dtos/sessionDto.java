package dev.kishore.authservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class sessionDto {

    private Long userId;
    private String token;
}
