package com.snapcollector.webservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Integer> {
    Score findByAccountIdAndPhotographer(Integer account_id, String photographer);
}
