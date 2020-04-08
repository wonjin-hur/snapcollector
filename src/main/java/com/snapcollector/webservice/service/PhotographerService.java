package com.snapcollector.webservice.service;

import com.github.pagehelper.PageInfo;
import com.snapcollector.webservice.domain.Account;
import com.snapcollector.webservice.domain.PhotographerScore;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PhotographerService {
    List<PhotographerScore> getAllPhotographerScores(PhotographerScore ps);
}
