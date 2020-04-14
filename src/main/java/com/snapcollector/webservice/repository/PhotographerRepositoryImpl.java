package com.snapcollector.webservice.repository;

import com.snapcollector.webservice.domain.Account;
import com.snapcollector.webservice.domain.Photographer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class PhotographerRepositoryImpl implements PhotographerCustomRepository {

    @Override
    public Page<Photographer> findAllPhotographer(Pageable pageable, Account account) {
        return null;
    }
}
