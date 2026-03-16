package com.project.fitness.Repository;

import com.project.fitness.DTO.RecommendationResponse;
import com.project.fitness.Entity.Recommendation;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, String> {
    @Nullable List<Recommendation> findByUserId(String userId);

    @Nullable List<Recommendation> findByActivityId(String activityId);
}