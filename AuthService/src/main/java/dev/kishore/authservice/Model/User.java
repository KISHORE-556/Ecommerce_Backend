package dev.kishore.authservice.Model;


import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends BaseModel {

  //  private Long id;
    private String name;
    private String email;
    private String password;
    @ManyToMany
    private Set<Role> roles = new HashSet<>();
}
