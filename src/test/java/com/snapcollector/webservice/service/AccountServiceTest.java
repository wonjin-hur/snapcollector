package com.snapcollector.webservice.service;

import com.snapcollector.webservice.domain.Account;
import com.snapcollector.webservice.domain.AccountRepository;
import com.snapcollector.webservice.domain.AccountRole;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void findByUsername() {
        Set<AccountRole> set = new HashSet<>();
        set.add(AccountRole.ADMIN);
        set.add(AccountRole.USER);
        Account account = Account.builder().password("keesun").email("keesun@email.com").roles(set).build();

        this.accountService.saveAccount(account);

        UserDetailsService userDetailsService = (UserDetailsService) accountService;
        UserDetails userDetails = userDetailsService.loadUserByUsername("keesun@email.com");

        assertThat(this.passwordEncoder.matches("keesun", userDetails.getPassword())).isTrue();
    }

    @Test
    public void findByUsernameFail(){

        String username = "random@dkskd.com";
        expectedException.expect(UsernameNotFoundException.class);
        expectedException.expectMessage(Matchers.containsString(username));

        accountService.loadUserByUsername(username);
    }
}