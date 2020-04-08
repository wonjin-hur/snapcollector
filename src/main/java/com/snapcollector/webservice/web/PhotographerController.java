package com.snapcollector.webservice.web;

import com.github.pagehelper.PageInfo;
import com.snapcollector.webservice.Resource.EventResource;
import com.snapcollector.webservice.Resource.PhotographerResource;
import com.snapcollector.webservice.domain.*;
import com.snapcollector.webservice.mapper.PhotographerMapper;
import com.snapcollector.webservice.service.PhotographerService;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/photographers", produces = MediaTypes.HAL_JSON_VALUE)
public class PhotographerController {

    private final PhotographerService photographerService;
    private final ModelMapper modelMapper;
    private final PhotographerMapper photographerMapper;

    public PhotographerController(PhotographerService photographerService, PhotographerMapper photographerMapper, ModelMapper modelMapper){
        this.photographerService = photographerService;
        this.modelMapper = modelMapper;
        this.photographerMapper = photographerMapper;
    }

//    @GetMapping
//    public ResponseEntity queryPhotographers(Pageable pageable,
//                                      PagedResourcesAssembler<Photographer> assembler,
//                                      @CurrentUser Account account) {
//        Photographer photographer = this.photographerMapper.findByName("_pickza");
//        System.out.println("pppppppp: "+photographer.getName());
//        Page<Photographer> page = this.photographerRepository.findAll(pageable);
//        var pagedResources = assembler.toModel(page, e -> new PhotographerResource(e));
//        pagedResources.add(new Link("/docs/index.html#query-photographers").withRel("profile"));
//        if (account != null) {
//            pagedResources.add(linkTo(PhotographerController.class).withRel("query-photographers"));
//        }
//        return ResponseEntity.ok(pagedResources);
//    }

    @GetMapping
    public ResponseEntity queryPhotographerScores(@RequestParam(value="page") int page, @RequestParam(value="pageSize") int pageSize, @RequestParam String location,
                                                  @CurrentUser Account account) {

        PhotographerScore ps = new PhotographerScore();
        ps.setPage(page);
        ps.setRows(pageSize);
        ps.setLocation(location);
        ps.setUserId(account.getId());

        List<PhotographerScore> psList = photographerService.getAllPhotographerScores(ps);
        System.out.println(psList.toString());
        return ResponseEntity.ok(new PageInfo<PhotographerScore>(psList));
    }
}
