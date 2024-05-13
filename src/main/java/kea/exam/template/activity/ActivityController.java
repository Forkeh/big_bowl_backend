package kea.exam.template.activity;

import kea.exam.template.activity.dto.ActivityResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }


    @GetMapping
    public ResponseEntity<List<ActivityResponseDTO>> getAllActivities() {
        return ResponseEntity.ok(activityService.getAllActivities());
    }


}
