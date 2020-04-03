package com.snapcollector.webservice.web;

import com.snapcollector.webservice.Resource.ErrorsResource;
import com.snapcollector.webservice.Resource.EventResource;
import com.snapcollector.webservice.domain.*;
import com.snapcollector.webservice.dto.event.EventDto;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final EventValidator eventValidator;

    public EventController(EventRepository eventRepository, ModelMapper modelMapper, EventValidator eventValidator){
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.eventValidator = eventValidator;
    }

    @PostMapping
    public ResponseEntity createEvent(@RequestBody @Valid EventDto eventDto, Errors errors){
        if(errors.hasErrors()){
            return badRequest(errors);
        }

        eventValidator.validate(eventDto, errors);
        if(errors.hasErrors()){
            return badRequest(errors);
        }

        Event event = modelMapper.map(eventDto, Event.class);
        event.update();
        Event newEvent = this.eventRepository.save(event);
        ControllerLinkBuilder selfLinkBuilder = linkTo(EventController.class).slash(newEvent.getId());
        URI createdUri = selfLinkBuilder.toUri();
        EventResource eventResource = new EventResource(event);
        eventResource.add(linkTo(EventController.class).withRel("query-events"));
        eventResource.add(selfLinkBuilder.withRel("update-event"));
        return ResponseEntity.created(createdUri).body(eventResource);
    }

    private ResponseEntity badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(new ErrorsResource(errors));
    }

    @GetMapping
    public ResponseEntity queryEvents(Pageable pageable,
                                      PagedResourcesAssembler<Event> assembler,
                                      @CurrentUser Account account) {
        Page<Event> page = this.eventRepository.findAll(pageable);
        var pagedResources = assembler.toModel(page, e -> new EventResource(e));
        pagedResources.add(new Link("/docs/index.html#resources-events-list").withRel("profile"));
        if (account != null) {
            pagedResources.add(linkTo(EventController.class).withRel("create-event"));
        }
        return ResponseEntity.ok(pagedResources);
    }
}
