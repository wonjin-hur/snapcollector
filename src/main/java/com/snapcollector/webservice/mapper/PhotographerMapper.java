package com.snapcollector.webservice.mapper;

import com.snapcollector.webservice.domain.Photographer;
import com.snapcollector.webservice.domain.PhotographerScore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotographerMapper {

    List<PhotographerScore> getAllPhotographerScores(PhotographerScore ps);
}
