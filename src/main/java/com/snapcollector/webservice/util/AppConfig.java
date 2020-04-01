package com.snapcollector.webservice.util;

import com.snapcollector.webservice.domain.Account;
import com.snapcollector.webservice.domain.AccountRepository;
import com.snapcollector.webservice.domain.AccountRole;
import com.snapcollector.webservice.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {

            @Autowired
            AppProperties appProperties;

            @Autowired
            AccountService accountService;

            @Override
            public void run(ApplicationArguments args) throws Exception {
                Set<AccountRole> adminRole = new HashSet<>();
                adminRole.add(AccountRole.ADMIN);
                adminRole.add(AccountRole.USER);

                Set<AccountRole> userRole = new HashSet<>();
                userRole.add(AccountRole.USER);

                Account adminAccount = Account.builder().email(appProperties.getAdminUsername()).password(appProperties.getAdminPassword()).roles(adminRole).build();
                Account userAccount = Account.builder().email(appProperties.getUserUsername()).password(appProperties.getUserPassword()).roles(userRole).build();

                accountService.saveAccount(adminAccount);
                accountService.saveAccount(userAccount);
            }
        };
    }
}
