package com.snapcollector.webservice.repository;

import com.snapcollector.webservice.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Integer> {
    Score findByAccountIdAndPhotographer(Integer account_id, String photographer);
}
