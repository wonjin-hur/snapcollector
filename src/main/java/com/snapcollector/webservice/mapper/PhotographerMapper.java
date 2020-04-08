package com.snapcollector.webservice.mapper;

import com.snapcollector.webservice.domain.Photographer;
import com.snapcollector.webservice.domain.PhotographerScore;

import java.util.List;


public interface PhotographerMapper {

    Photographer findByName(String name);
    List<PhotographerScore> getAllPhotographerScores(PhotographerScore ps);
}
