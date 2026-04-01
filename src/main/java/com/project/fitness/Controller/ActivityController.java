package com.project.fitness.Controller;

import com.project.fitness.DTO.ActivityRequest;
import com.project.fitness.DTO.ActivityResponse;
import com.project.fitness.Service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest createActivityRequest) {
        return ResponseEntity.ok(activityService.trackActivity(createActivityRequest));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> retriveActivity (
           @RequestHeader(value = "X-User-Id") String user_id
    ) {
        return ResponseEntity.ok(activityService.getAllActivites(user_id));
    }

}
