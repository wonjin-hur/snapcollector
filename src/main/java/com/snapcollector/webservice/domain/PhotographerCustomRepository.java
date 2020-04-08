package com.snapcollector.webservice.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhotographerCustomRepository {
    Page<Photographer> findAllPhotographer(Pageable pageable, Account account);
}
