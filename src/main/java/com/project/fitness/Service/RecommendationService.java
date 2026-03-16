package com.project.fitness.Service;

import com.project.fitness.DTO.RecommendationRequest;
import com.project.fitness.DTO.RecommendationResponse;
import com.project.fitness.Entity.Activity;
import com.project.fitness.Entity.Recommendation;
import com.project.fitness.Entity.User;
import com.project.fitness.Repository.ActivityRepository;
import com.project.fitness.Repository.RecommendationRepository;
import com.project.fitness.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    public RecommendationResponse generateRecommendation(RecommendationRequest request) {

        User user = userRepository.findById(request.getUser_id())
                .orElseThrow(() ->
                        new RuntimeException("Invalid User : " + request.getUser_id()));

        Activity activity = activityRepository.findById(request.getActivity_id())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Activity : " + request.getActivity_id()));

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .activity(activity)
                .improvements(request.getImprovements())
                .suggations(request.getSuggestions())
                .safety(request.getSafety())
                .build();

        Recommendation savedRecommendation =
                recommendationRepository.save(recommendation);

        return mappedToResponse(savedRecommendation);
    }

    private RecommendationResponse mappedToResponse(Recommendation savedRecommendation) {

        RecommendationResponse response = new RecommendationResponse();

        response.setId(savedRecommendation.getId());
        response.setUser_id(savedRecommendation.getUser());
        response.setActivity_id(savedRecommendation.getActivity());
        response.setType(savedRecommendation.getType());
        response.setRecommendation(savedRecommendation.getRecommendation());
        response.setImprovements(savedRecommendation.getImprovements());
        response.setSuggestions(savedRecommendation.getSuggations());
        response.setSafety(savedRecommendation.getSafety());
        response.setCreatedAt(savedRecommendation.getCreatedAt());
        response.setUpdatedAt(savedRecommendation.getUpdatedAt());

        return response;
    }


    public @Nullable List<Recommendation> getRecommendationByUserId(String userId) {

        return recommendationRepository.findByUserId(userId);
    }

    public @Nullable List<Recommendation> getRecommendationByActivity(String activity_id) {

        return recommendationRepository.findByActivityId(activity_id);
    }
}