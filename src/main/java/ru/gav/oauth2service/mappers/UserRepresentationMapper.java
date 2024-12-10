package ru.gav.oauth2service.mappers;

import org.keycloak.representations.idm.UserRepresentation;
import ru.gav.oauth2service.dto.AccountDto;
import ru.gav.oauth2service.entites.Account;

public interface UserRepresentationMapper {

    AccountDto representToDto(UserRepresentation userRepresentation);

    UserRepresentation dtoToRepresent(AccountDto accountDto);

    Account representToAccount(UserRepresentation userRepresentation);

    UserRepresentation accountToRepresent(Account account);
}
