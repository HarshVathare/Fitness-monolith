package com.project.fitness.DTO;

import com.project.fitness.Entity.type.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest {

    private String user_id;
    private ActivityType type;
    private Map<String, Object> Additional_Matrics;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startDate;

}
