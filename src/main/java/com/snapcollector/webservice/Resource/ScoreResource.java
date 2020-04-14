package com.snapcollector.webservice.Resource;

import com.snapcollector.webservice.domain.Score;
import com.snapcollector.webservice.web.ScoreController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class ScoreResource extends EntityModel<Score> {

    public ScoreResource(Score score, Link... links){
        super(score, links);
        add(linkTo(ScoreController.class).slash(score.getId()).withSelfRel());
    }
}
