package com.snapcollector.webservice.Resource;

import com.snapcollector.webservice.domain.Event;
import com.snapcollector.webservice.domain.Photographer;
import com.snapcollector.webservice.web.EventController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class PhotographerResource extends EntityModel<Photographer> {

    public PhotographerResource(Photographer photographer, Link... links){
        super(photographer, links);
        add(linkTo(EventController.class).slash(photographer.getId()).withSelfRel());
    }
}
