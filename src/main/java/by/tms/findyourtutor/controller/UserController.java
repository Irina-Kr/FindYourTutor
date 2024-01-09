package by.tms.findyourtutor.controller;

import by.tms.findyourtutor.configuration.JWTTokenProvider;
import by.tms.findyourtutor.configuration.UserPrincipal;
import by.tms.findyourtutor.dto.LoginUserDto;
import by.tms.findyourtutor.entity.Role;
import by.tms.findyourtutor.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JWTTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/registration")
    public ResponseEntity<User> registration (@RequestBody User user){
        userService.save(user);

        return ResponseEntity.ok(user);

    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUserDto userDto){
        UserPrincipal userPrincipal = (UserPrincipal) userService.loadUserByUsername(userDto.getUsername());

        if (passwordEncoder.matches(userDto.getPassword(), userPrincipal.getPassword())) {
            Set<Role> authorities = (Set<Role>) userPrincipal.getAuthorities();

            String token = jwtTokenProvider.generateToken(userPrincipal.getUsername(), userPrincipal.getPassword(), authorities);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().build();
    }
}

