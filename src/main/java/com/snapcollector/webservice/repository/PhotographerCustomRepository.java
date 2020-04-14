package com.snapcollector.webservice.repository;

import com.snapcollector.webservice.domain.Account;
import com.snapcollector.webservice.domain.Photographer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhotographerCustomRepository {
    Page<Photographer> findAllPhotographer(Pageable pageable, Account account);
}
