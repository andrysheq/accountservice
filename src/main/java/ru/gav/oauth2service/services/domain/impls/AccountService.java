package ru.gav.oauth2service.services.domain.impls;

import org.springframework.stereotype.Service;
import ru.gav.oauth2service.dto.AccountDto;
import ru.gav.oauth2service.entites.Account;
import ru.gav.oauth2service.exceptions.AlreadyExistsException;
import ru.gav.oauth2service.exceptions.NotFoundException;
import ru.gav.oauth2service.mappers.AccountMapper;
import ru.gav.oauth2service.repos.AccountRepository;
import ru.gav.oauth2service.services.domain.IAccountService;
@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository,
                          AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }


    @Override
    public void delete(Long id) {
        Account found = getById(id);
        accountRepository.delete(found);

    }

    @Override
    public void update(Long id, AccountDto accountDto) {
        Account old = getById(id);
        if (!existByUsernameAndEmail(id, accountDto.getUsername(), accountDto.getEmail())) {
            Account updated = accountMapper.dtoToAccount(accountDto);
            updated.setId(old.getId());
            updated.setIsLocked(old.getIsLocked());
            updated.setIsDeleted(old.getIsDeleted());
            updated.setAuthId(old.getAuthId());
            accountRepository.save(updated);
        } else throw new AlreadyExistsException("Аккаунт с такими данными уже существует");

    }

    @Override
    public void create(AccountDto accountDto, String authId) {
        if (!existByUsernameAndEmail(0L, accountDto.getUsername(), accountDto.getEmail())) {
            Account created = accountMapper.dtoToAccount(accountDto);
            created.setIsLocked(false);
            created.setIsDeleted(false);
            created.setAuthId(authId);
            accountRepository.save(created);
        } else throw new AlreadyExistsException("Аккаунт с такими данными уже существует");
    }

    @Override
    public AccountDto getOne(Long id) {
        return accountMapper.accToDto(accountRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Не найдено")));
    }

    @Override
    public AccountDto getOne(String username) {
        return accountMapper.accToDto(accountRepository.findAccountByUsername(username).
                orElseThrow(() -> new NotFoundException("Не найдено")));
    }

    private Account getById(Long id) {
        return accountRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Не найдено"));
    }

    private Boolean existByUsernameAndEmail(Long id, String username, String email) {
        return accountRepository.findAccountByUsernameOrEmailAndIdNot(username, email, id).
                isPresent();
    }
}
