package com.snapcollector.webservice.repository;

import com.snapcollector.webservice.domain.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotographerRepository extends JpaRepository<Photographer, Integer>,PhotographerCustomRepository {

}
