package com.project.fitness.Service;

import com.project.fitness.DTO.ActivityRequest;
import com.project.fitness.DTO.ActivityResponse;
import com.project.fitness.Entity.Activity;
import com.project.fitness.Entity.User;
import com.project.fitness.Repository.ActivityRepository;
import com.project.fitness.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public @Nullable ActivityResponse trackActivity(ActivityRequest request) {
        User user = userRepository.findById(request.getUser_id())
                .orElseThrow(()->new RuntimeException("Invalid User : "+ request.getUser_id())
        );

        Activity activity = Activity.builder()
                .user(user)
                .type(request.getType())
                .Additional_Matrics(request.getAdditional_Matrics())
                .caloriesBurned(request.getCaloriesBurned())
                .duration(request.getDuration())
                .startDate(request.getStartDate())
                .build();

        Activity savedActivity = activityRepository.save(activity);

        return mapedToResponse(savedActivity);
    }

    private @Nullable ActivityResponse mapedToResponse(Activity savedActivity) {
        ActivityResponse response = new ActivityResponse();
        response.setId(savedActivity.getId());
        response.setUser_id(savedActivity.getUser().getId());
        response.setAdditional_Matrics(savedActivity.getAdditional_Matrics());
        response.setCaloriesBurned(savedActivity.getCaloriesBurned());
        response.setDuration(savedActivity.getDuration());
        response.setType(savedActivity.getType());
        response.setCreatedAt(savedActivity.getCreatedAt());
        response.setStartDate(savedActivity.getStartDate());
        response.setUpdatedAt(savedActivity.getUpdatedAt());
        return response;
    }

    public List<ActivityResponse> getAllActivites(String user_id) {

        List<Activity> activity = activityRepository.findByUserId(user_id);

        //Convert Activity to ActivityResponse by using Stream().map() method
        List<ActivityResponse> activityResponseList = activity
                .stream()
                .map(activity1 ->
                        new ActivityResponse(activity1.getId(),activity1.getUser().getId()
                                ,activity1.getType(),activity1.getAdditional_Matrics()
                                ,activity1.getDuration(),activity1.getCaloriesBurned()
                                ,activity1.getStartDate(),activity1.getCreatedAt()
                                ,activity1.getUpdatedAt()))
                .toList();

        return activityResponseList;
    }
}
