package com.snapcollector.webservice.web;

import com.snapcollector.webservice.Resource.EventResource;
import com.snapcollector.webservice.Resource.PhotographerResource;
import com.snapcollector.webservice.domain.*;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/photographers", produces = MediaTypes.HAL_JSON_VALUE)
public class PhotographerController {

    private final PhotographerRepository photographerRepository;
    private final ModelMapper modelMapper;

    public PhotographerController(PhotographerRepository photographerRepository, ModelMapper modelMapper){
        this.photographerRepository = photographerRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity queryPhotographers(Pageable pageable,
                                      PagedResourcesAssembler<Photographer> assembler,
                                      @CurrentUser Account account) {
        Page<Photographer> page = this.photographerRepository.findAll(pageable);
        var pagedResources = assembler.toModel(page, e -> new PhotographerResource(e));
        pagedResources.add(new Link("/docs/index.html#query-photographers").withRel("profile"));
        if (account != null) {
            pagedResources.add(linkTo(PhotographerController.class).withRel("query-photographers"));
        }
        return ResponseEntity.ok(pagedResources);
    }
}
