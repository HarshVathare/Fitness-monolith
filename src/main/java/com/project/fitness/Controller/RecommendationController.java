package com.project.fitness.Controller;

import com.project.fitness.DTO.RecommendationRequest;
import com.project.fitness.DTO.RecommendationResponse;
import com.project.fitness.Service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/generate")
    public ResponseEntity<RecommendationResponse> generateRecommendation(
            @RequestBody RecommendationRequest request
    ){
        return ResponseEntity.ok(recommendationService.generateRecommendation(request));
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<RecommendationResponse>> getRecommendationByUserId(
            @PathVariable String user_id
    ){
        return ResponseEntity.ok(recommendationService.getRecommendationByUserId(user_id));
    }
}
