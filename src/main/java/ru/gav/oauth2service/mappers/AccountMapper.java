package ru.gav.oauth2service.mappers;

import org.mapstruct.Mapper;
import ru.gav.oauth2service.dto.AccountDto;
import ru.gav.oauth2service.entites.Account;

@Mapper(componentModel = "SPRING")
public interface AccountMapper {

    AccountDto accToDto(Account account);

    Account dtoToAccount(AccountDto accountDto);
}
