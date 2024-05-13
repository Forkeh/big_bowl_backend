package kea.exam.template.activity;

import kea.exam.template.activity.dto.ActivityRequestDTO;
import kea.exam.template.activity.dto.ActivityResponseDTO;
import kea.exam.template.exceptions.EntityNotFoundException;
import kea.exam.template.type.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {


    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private ActivityService activityService;

    @Test
    void updateIsOpenOnNonExistingActivity() {
        // Arrange
        long id = 2L;
        var request = new ActivityRequestDTO(
                true
        );

        Mockito.when(activityRepository.findById(id))
                .thenReturn(Optional.empty());


        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> activityService.updateIsActivityOpen(id, request));

    }


    @Test
    void updateIsOpenOnExistingActivity() {
        // Arrange
        long id = 2L;
        var request = new ActivityRequestDTO(
                true
        );

        Activity activityInDB = new Activity(
                id,
                "Bowling lane 100",
                !request.isOpen(),
                new Type(
                        "Bowling Junior",
                        100
                )
        );


        Mockito.when(activityRepository.findById(id))
                .thenReturn(Optional.of(activityInDB));


        // Act
        ActivityResponseDTO responseDTO = activityService.updateIsActivityOpen(id, request);

        // Assert
        assertEquals(id, responseDTO.id());
        assertEquals(request.isOpen(), responseDTO.isOpen());

    }
}