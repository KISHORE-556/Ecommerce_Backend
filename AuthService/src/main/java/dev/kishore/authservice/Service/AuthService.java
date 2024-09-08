package dev.kishore.authservice.Service;

import dev.kishore.authservice.Exceptions.UserAlreadyRegisteredException;

import dev.kishore.authservice.Model.Session;
import dev.kishore.authservice.Model.SessionStatus;
import dev.kishore.authservice.Model.User;
import dev.kishore.authservice.Repository.SessionRepository;
import dev.kishore.authservice.Repository.UserRepository;
import dev.kishore.authservice.dtos.userDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService {


   private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    public AuthService( UserRepository userRepository,
                        SessionRepository sessionRepository) {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

// ============================== Method to Login ===================================
    public ResponseEntity<userDto> login(String email, String password) {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User not registered... please sign up ");
        }

        User user = optionalUser.get();


        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String token = RandomStringUtils.randomAscii(20);

        MultiValueMapAdapter<String,String> header = new MultiValueMapAdapter<>(new HashMap<>());
        header.add("AUTH_TOKEN", token);

        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);
        sessionRepository.save(session);

        userDto userdto = convertToDto(user);
        ResponseEntity<userDto> response = new ResponseEntity<>(userdto,header,HttpStatus.OK);
        return response;

    }

    //======================== Method to Signup =================================

    public userDto signUp(String email, String password)  {
        Optional<User> userEmail = userRepository.findByEmail(email);
        if(!userEmail.isEmpty()){
            throw new UserAlreadyRegisteredException("User already registered... please login.");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        User savedUser = userRepository.save(user);

        return convertToDto(savedUser);
    }


    //========================= Method to Validate ================================

    public SessionStatus validate(String token, Long userId) {

        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_id(token,userId);

        if(sessionOptional.isEmpty()){
            return SessionStatus.INVALID;
        }

        Session session = sessionOptional.get();

        if(!session.getSessionStatus().equals(SessionStatus.ACTIVE))
            return SessionStatus.INVALID;

        return SessionStatus.ACTIVE;


    }

    private userDto convertToDto(User savedUser) {
        userDto userdto = new userDto();
        userdto.setEmail(savedUser.getEmail());
        userdto.setRoles(savedUser.getRoles());
        return userdto;
    }


}
