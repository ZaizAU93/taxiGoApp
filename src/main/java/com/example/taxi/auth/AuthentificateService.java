package com.example.taxi.auth;

import com.example.taxi.config.JwtService;
import com.example.taxi.entity.User;
import com.example.taxi.repository.UserRepository;
import com.example.taxi.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthentificateService {
    @Autowired
    private final UserRepository repository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService service;
    @Autowired
    private final AuthenticationManager manager;


    //  private final Duration cookieExpiry = Duration.ofMillis(1800);

    public AuthentificcationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.User)
                .surname(request.getSurname())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .name(request.getName())
                .imgUrl(request.getImgUrl())
                .build();
        repository.save(user);
        var jwtToken = service.generateToken(user);

//        ResponseCookie cookie = ResponseCookie.from("Authorization", jwtToken)
//                .httpOnly(true)
//                .secure(false)
//                .path("/")
//                .maxAge(cookieExpiry)
//                .build();
//        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return AuthentificcationResponse.builder().token(jwtToken).id(user.getId()).build();
    }

    public AuthentificcationResponse authentificate(AuthenticationRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = repository.findByUsername(request.getUsername());
        var jwtToken = service.generateToken(user);
        return AuthentificcationResponse.builder().token(jwtToken).id(user.getId()).build();
    }
}
