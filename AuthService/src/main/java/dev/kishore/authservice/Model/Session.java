package dev.kishore.authservice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.util.Date;

@Getter
@Setter
@Entity
public class Session extends BaseModel{

    private String token;
    private Date ExpiringAt;
    @ManyToOne
//    @JoinColumn(name = "user_id")
    private User user;
    private SessionStatus sessionStatus;
}
