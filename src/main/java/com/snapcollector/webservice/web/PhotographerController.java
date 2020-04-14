package com.snapcollector.webservice.web;

import com.github.pagehelper.PageInfo;
import com.snapcollector.webservice.domain.*;
import com.snapcollector.webservice.service.PhotographerService;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;


@Controller
@RequestMapping(value = "/api/photographers", produces = MediaTypes.HAL_JSON_VALUE)
public class PhotographerController {

    private final PhotographerService photographerService;
    private final ModelMapper modelMapper;

    public PhotographerController(PhotographerService photographerService, ModelMapper modelMapper){
        this.photographerService = photographerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity queryPhotographerScores(@RequestParam(value="page") int page, @RequestParam(value="pageSize") int pageSize, @RequestParam String location,
                                                  @RequestParam(value="sort_name") String sortName, @RequestParam(value="sort_option") String sortOption,
                                                  @RequestParam(value="checkbox") String checkbox, @CurrentUser Account account) {
        PhotographerScore ps = new PhotographerScore();
        ps.setPage(page);
        ps.setRows(pageSize);
        ps.setLocation(location);
        ps.setUserId(account.getId());
        ps.setSortName(sortName);
        ps.setSortOption(sortOption);
        ps.setSelectOption(checkbox);

        List<PhotographerScore> psList = photographerService.getAllPhotographerScores(ps);
        System.out.println(psList.toString());
        return ResponseEntity.ok(new PageInfo<PhotographerScore>(psList));
    }
}
