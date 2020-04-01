package com.snapcollector.webservice.util;

import com.snapcollector.webservice.domain.Account;
import com.snapcollector.webservice.domain.AccountRole;
import com.snapcollector.webservice.service.AccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthServerConfigTest extends BaseControllerTests{

    @Autowired
    AccountService accountService;


    @Test
    @Description("token create")
    public void getAuthToken() throws Exception{
        Set set = new HashSet();
        set.add(AccountRole.ADMIN);
        set.add(AccountRole.USER);

        Account account = Account.builder().email("keesun1@email.com")
                .password("keesun")
                .roles(set).build();

        this.accountService.saveAccount(account);

        String clientId = "myApp";
        String clientSecret = "pass";

        this.mockMvc.perform(post("/oauth/token")
        .with(httpBasic(clientId,clientSecret))
                .param("username", "keesun1@email.com")
                .param("password", "keesun")
                .param("grant_type", "password")
        ).andExpect(status().isOk())
                .andDo(print()).andExpect(jsonPath("access_token").exists());
    }

}