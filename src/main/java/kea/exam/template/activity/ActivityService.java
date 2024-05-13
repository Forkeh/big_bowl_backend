package kea.exam.template.activity;


import kea.exam.template.activity.dto.ActivityResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {


    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Page<ActivityResponseDTO> getAllActivities(
            Integer pageNum,
            Integer pageSize,
            String sortDir,
            String sortBy,
            Optional<String> filterBy
    ) {

        Pageable pageable = PageRequest.of(
                pageNum,
                pageSize,
                Sort.Direction.valueOf(sortDir),
                sortBy
        );

        if (filterBy.isPresent()) {
            return activityRepository.findAllByTypeNameContainsIgnoreCase(pageable, filterBy.get())
                    .map(this::toDTO);
        }

        return activityRepository.findAll(pageable)
                .map(this::toDTO);

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
