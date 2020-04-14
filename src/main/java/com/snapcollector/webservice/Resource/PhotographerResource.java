package com.snapcollector.webservice.Resource;

import com.snapcollector.webservice.domain.Photographer;
import com.snapcollector.webservice.web.PhotographerController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class PhotographerResource extends EntityModel<Photographer> {

    public PhotographerResource(Photographer photographer, Link... links){
        super(photographer, links);
        add(linkTo(PhotographerController.class).slash(photographer.getId()).withSelfRel());
    }
}
