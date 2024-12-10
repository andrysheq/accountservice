package ru.gav.oauth2service.services.authorize;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.gav.oauth2service.dto.AccountDto;
import ru.gav.oauth2service.dto.JwtDto;

@Service
public interface IAuthorizeService {

    void register(AccountDto accountDto);

    JwtDto login(AccountDto accountDto);

    JwtDto refresh(JwtDto jwtDto);
}
