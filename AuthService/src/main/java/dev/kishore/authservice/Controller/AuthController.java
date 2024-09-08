package dev.kishore.authservice.Controller;

import dev.kishore.authservice.Model.Session;
import dev.kishore.authservice.Model.SessionStatus;
import dev.kishore.authservice.Model.User;
import dev.kishore.authservice.Service.AuthService;
import dev.kishore.authservice.dtos.sessionDto;
import dev.kishore.authservice.dtos.signupRequestDto;
import dev.kishore.authservice.dtos.userDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/signup")
    public userDto signUp(@RequestBody signupRequestDto request)  {

        return authService.signUp(request.getEmail(), request.getPassword());

    }

    @PostMapping("/login")
    public ResponseEntity<userDto> login(@RequestBody signupRequestDto request ){

        return authService.login(request.getEmail(), request.getPassword());

    }

    @PostMapping("/logout")
    public void logout(){

    }
    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validate(@RequestBody sessionDto sessiondetails){

       SessionStatus sessionStatus =  authService.validate(sessiondetails.getToken(), sessiondetails.getUserId());

       return new ResponseEntity<>(sessionStatus, HttpStatus.OK);

    }
}
