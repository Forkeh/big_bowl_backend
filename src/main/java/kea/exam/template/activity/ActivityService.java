package kea.exam.template.activity;


import kea.exam.template.activity.dto.ActivityRequestDTO;
import kea.exam.template.activity.dto.ActivityResponseDTO;
import kea.exam.template.exceptions.EntityNotFoundException;
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

    public List<ActivityResponseDTO> getAllActivities(
            Optional<String> filterBy
    ) {

        if (filterBy.isPresent()){
            return activityRepository.findAllByTypeNameContainsIgnoreCase(filterBy.get())
                    .stream()
                    .map(this::toDTO)
                    .toList();
        }

        return activityRepository.findAll()
                .stream().map(this::toDTO).toList();
    }

    public ActivityResponseDTO updateIsActivityOpen(Long id, ActivityRequestDTO activityRequestDTO) {
        Activity activityInDB = activityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity", id));

        activityInDB.setOpen(activityRequestDTO.isOpen());
        activityRepository.save(activityInDB);
        return toDTO(activityInDB);
    }


    public ActivityResponseDTO toDTO(Activity activity) {
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


    public List<ActivityResponseDTO> getAllActivitiesByType(String id) {
        return activityRepository.findAllByTypeName(id).stream().map(this::toDTO).toList();
    }
}
