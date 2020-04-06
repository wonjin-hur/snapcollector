package com.snapcollector.webservice.web;

import com.snapcollector.webservice.Resource.ErrorsResource;
import com.snapcollector.webservice.Resource.EventResource;
import com.snapcollector.webservice.Resource.PhotographerResource;
import com.snapcollector.webservice.Resource.ScoreResource;
import com.snapcollector.webservice.domain.*;
import com.snapcollector.webservice.dto.score.ScoreDto;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/scores", produces = MediaTypes.HAL_JSON_VALUE)
public class ScoreController {

    private final ScoreRepository scoreRepository;
    private final ModelMapper modelMapper;

    public ScoreController(ScoreRepository scoreRepository, ModelMapper modelMapper){
        this.scoreRepository = scoreRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity saveScore(@RequestBody ScoreDto scoredto,
                                      @CurrentUser Account currentUser, Errors errors) {
        if (errors.hasErrors()) {
            return badRequest(errors);
        }

        Score score = modelMapper.map(scoredto, Score.class);
        score.setAccount(currentUser);


        Score scoreDup = this.scoreRepository.findByAccountIdAndPhotographer(currentUser.getId(),scoredto.getPhotographer());
        if(scoreDup!=null){
            score.setId(scoreDup.getId());
        }

        Score newScore = this.scoreRepository.save(score);

        var selfLinkBuilder = linkTo(EventController.class).slash(newScore.getId());
        URI createdUri = selfLinkBuilder.toUri();
        ScoreResource scoreResource = new ScoreResource(score);
        scoreResource.add(linkTo(EventController.class).withRel("save-score"));
        scoreResource.add(selfLinkBuilder.withRel("save-score"));
        scoreResource.add(new Link("/docs/index.html#resources-save-score").withRel("profile"));
        return ResponseEntity.created(createdUri).body(scoreResource);
    }

    private ResponseEntity badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(new ErrorsResource(errors));
    }
}
