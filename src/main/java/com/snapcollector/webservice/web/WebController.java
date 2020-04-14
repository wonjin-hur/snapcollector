package com.snapcollector.webservice.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class WebController {

//
//    @GetMapping("/")
//    public String main(Model model){
//        return "main";
//    }
//
//    @GetMapping("/home")
//    public String getAuthorizationMessage() {
//        return "home";
//    }
//
//    @GetMapping({"/loginSuccess", "/hello"})
//    public String loginSuccess(Model model) {
//        String email = (String) httpSession.getAttribute("email");
//        String gender = (String) httpSession.getAttribute("gender");
//        String age = (String) httpSession.getAttribute("age");
//
//        String token = createJWTToken.createJWTToken(email,gender,age);
//
//        System.out.println(token);
//        return "redirect:http://localhost:8080/agreement?token="+token;
//    }
//
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @GetMapping("/loginFailure")
//    public String loginFailure() {
//        return "loginFailure";
//    }

    @CrossOrigin
    @GetMapping("/vue_login")
    public String getVueLogin() {
        System.out.println("vue_login");
        String clientId = "T53QjFJsSbahbSG8w70z";
        String redirectURI = "http://localhost:5000/login/oauth2/code/naver";
        String state = "123";

        //return "redirect:https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="+clientId+"&redirect_uri="+redirectURI+"&state="+state;
        return "redirect:/oauth2/authorization/naver";
    }

}
