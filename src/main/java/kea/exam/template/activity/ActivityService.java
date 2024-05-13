package kea.exam.template.activity;


import kea.exam.template.activity.dto.ActivityResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {


    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<ActivityResponseDTO> getAllActivities() {
        return activityRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }


    private ActivityResponseDTO toDTO(Activity activity) {
        return new ActivityResponseDTO(
                activity.getId(),
                activity.getName(),
                activity.isOpen(),
                activity.getType()
                        .getName() == null ? null : activity.getType()
                        .getName(),
                activity.getType()
                        .getPrice() == null ? null : activity.getType()
                        .getPrice()
        );
    }
}
