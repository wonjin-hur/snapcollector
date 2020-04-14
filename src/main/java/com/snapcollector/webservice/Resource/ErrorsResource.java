package com.snapcollector.webservice.Resource;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.validation.Errors;

public class ErrorsResource extends EntityModel<Errors> {
    public ErrorsResource(Errors content, Link... links){
        super(content, links);
    }
}
