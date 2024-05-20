package kea.exam.template.user;


import kea.exam.template.user.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    private Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    public UserDTO createUser(UserDTO userDTO) {
        Optional<User> optionalUser = findUserById(userDTO.id());

        if (optionalUser.isPresent()) {
            return userDTO;
        }

        userRepository.save(toEntity(userDTO));
        return userDTO;
    }


    public UserDTO toDTO(User entity) {
        return new UserDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName()
        );
    }

    private User toEntity(UserDTO userDTO) {
        return new User(
                userDTO.id(),
                userDTO.firstName(),
                userDTO.lastName()
        );
    }
}
