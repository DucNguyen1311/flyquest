package com.backend.flyquest.Service.ServiceImp;


import com.backend.flyquest.Model.Account;
import com.backend.flyquest.Payload.RegisterRequest;
import com.backend.flyquest.Repository.AccountRepository;
import com.backend.flyquest.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public int saveAccountToDatabase(RegisterRequest registerRequest) {
        if (accountRepository.findAccountByAccountEmail(registerRequest.getEmail()) != null) {
            return 3;
        } else {
            accountRepository.save(new Account(registerRequest));
            return 0;
        }
    }

    @Override
    public Account findAccountByUsername(String username) {
        try {
            return accountRepository.findAccountByAccountName(username);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Account findAccountByEmail(String email) {
        return accountRepository.findAccountByAccountEmail(email);
    }
}