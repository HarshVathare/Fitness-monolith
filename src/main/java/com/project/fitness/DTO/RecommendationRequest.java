package com.project.fitness.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationRequest {
    private String user_id;
    private String activity_id;
    private List<String> improvements;
    private List<String> suggestions;
    private List<String> safety;
}
