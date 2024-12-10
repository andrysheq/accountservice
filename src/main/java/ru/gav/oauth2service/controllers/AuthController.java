package ru.gav.oauth2service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gav.oauth2service.dto.AccountDto;
import ru.gav.oauth2service.dto.JwtDto;
import ru.gav.oauth2service.services.authorize.IAuthorizeService;

@RestController
@RequestMapping("")
public class AuthController {

    private final IAuthorizeService authorizeService;

    public AuthController(IAuthorizeService authorizeService) {
        this.authorizeService = authorizeService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@RequestBody AccountDto accountDto) {
        authorizeService.register(accountDto);
        return ResponseEntity.ok("Аккаунт успешно создан");
    }

    @PostMapping("/auth/login")
    public ResponseEntity<JwtDto> login(@RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(authorizeService.login(accountDto));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto){
        return ResponseEntity.ok(authorizeService.refresh(jwtDto));
    }

}
