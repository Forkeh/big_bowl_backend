package kea.exam.template.activity;

import kea.exam.template.activity.dto.ActivityResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }


    @GetMapping
    public ResponseEntity<Page<ActivityResponseDTO>> getAllActivities(
            @RequestParam Integer pageIndex,
            @RequestParam Integer pageSize,
            @RequestParam String sortDir,
            @RequestParam String sortBy,
            @RequestParam Optional<String> filterBy
    ) {
        return ResponseEntity.ok(activityService.getAllActivities(pageIndex, pageSize, sortDir, sortBy, filterBy));
    }


}
