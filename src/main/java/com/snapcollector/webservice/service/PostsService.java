package com.snapcollector.webservice.service;

import com.snapcollector.webservice.domain.PostsRepository;
import com.snapcollector.webservice.dto.posts.PostsMainResponseDto;
import com.snapcollector.webservice.dto.posts.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostsService {

    private PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto dto){
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public List<PostsMainResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().map(PostsMainResponseDto::new).collect(Collectors.toList());
    }
}
