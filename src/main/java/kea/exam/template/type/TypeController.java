package kea.exam.template.type;


import kea.exam.template.activity.ActivityService;
import kea.exam.template.activity.dto.ActivityResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "types")
public class TypeController {


    private final ActivityService activityService;

    public TypeController(ActivityService activityService) {
        this.activityService = activityService;
    }


    @GetMapping("{id}/activities")
    public ResponseEntity<List<ActivityResponseDTO>> getAllActivitiesByType(@PathVariable("id") String id) {
        return ResponseEntity.ok(activityService.getAllActivitiesByType(id));
    }
}
