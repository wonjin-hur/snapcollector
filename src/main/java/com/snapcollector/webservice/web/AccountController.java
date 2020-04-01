package com.snapcollector.webservice.web;

import com.snapcollector.webservice.Resource.ErrorsResource;
import com.snapcollector.webservice.Resource.EventResource;
import com.snapcollector.webservice.domain.*;
import com.snapcollector.webservice.dto.event.EventDto;
import com.snapcollector.webservice.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/accounts", produces = MediaTypes.HAL_JSON_VALUE)
public class AccountController {

    private final ModelMapper modelMapper;
    private final AccountService accountService;

    public AccountController(ModelMapper modelMapper, AccountService accountService){
        this.modelMapper = modelMapper;
        this.accountService = accountService;
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity createEvent(@RequestBody @Valid Account account, Errors errors){
        if(errors.hasErrors()){
            return badRequest(errors);
        }

        if(errors.hasErrors()){
            return badRequest(errors);
        }

        this.accountService.saveAccount(account);
        ControllerLinkBuilder selfLinkBuilder = linkTo(AccountController.class).slash(1);
        URI createdUri = selfLinkBuilder.toUri();
//        EventResource eventResource = new EventResource(event);
//        eventResource.add(linkTo(EventController.class).withRel("query-events"));
//        eventResource.add(selfLinkBuilder.withRel("update-event"));
        return ResponseEntity.created(createdUri).body(account);
    }


    private ResponseEntity badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(new ErrorsResource(errors));
    }
}
