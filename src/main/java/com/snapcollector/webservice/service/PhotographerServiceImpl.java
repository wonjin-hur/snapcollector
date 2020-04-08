package com.snapcollector.webservice.service;

import com.github.pagehelper.PageHelper;
import com.snapcollector.webservice.domain.PhotographerScore;
import com.snapcollector.webservice.mapper.PhotographerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotographerServiceImpl implements PhotographerService {

    @Autowired
    private PhotographerMapper photographerMapper;

    @Override
    public List<PhotographerScore> getAllPhotographerScores(PhotographerScore ps) {
        if (ps.getPage() != null && ps.getRows() != null) {
            PageHelper.startPage(ps.getPage(), ps.getRows());
        }
        return photographerMapper.getAllPhotographerScores(ps);
    }
}
