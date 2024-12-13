package com.backend.flyquest.Security;

import com.backend.flyquest.Model.Account;
import com.backend.flyquest.Repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = accountRepository.findAccountByAccountName(username);
        if (u == null) {
            throw new UsernameNotFoundException(username);
        } else {
            return new AccountDetails(u);
        }
    }

    @Transactional
    public UserDetails loadUserById(String id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new AccountDetails(account);
    }
}
