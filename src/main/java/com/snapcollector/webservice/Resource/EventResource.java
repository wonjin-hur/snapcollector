package com.snapcollector.webservice.Resource;

import com.snapcollector.webservice.web.EventController;
import org.springframework.hateoas.EntityModel;
import com.snapcollector.webservice.domain.Event;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class EventResource extends EntityModel<Event> {

    public EventResource(Event event, Link... links){
        super(event, links);
        add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
    }
}
