package kea.exam.template.activity;

import kea.exam.template.activity.dto.ActivityRequestDTO;
import kea.exam.template.activity.dto.ActivityResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PatchMapping("{id}")
    public ResponseEntity<ActivityResponseDTO> updateIsActivityOpen(@PathVariable("id") Long id, @RequestBody ActivityRequestDTO activityRequestDTO) {
        return ResponseEntity.ok(activityService.updateIsActivityOpen(id, activityRequestDTO));
    }


}
