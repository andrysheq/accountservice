package ru.gav.oauth2service.services.domain;

import org.springframework.stereotype.Service;
import ru.gav.oauth2service.dto.AccountDto;

@Service
public interface IAccountService {

    void delete(Long id);

    void update(Long id, AccountDto accountDto);

    void create(AccountDto accountDto, String authId);

    AccountDto getOne(Long id);

    AccountDto getOne(String username);
}
