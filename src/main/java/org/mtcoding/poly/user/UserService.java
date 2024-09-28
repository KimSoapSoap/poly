package org.mtcoding.poly.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse.joinedUsersDTO join(UserRequest.JoinUsersDTO joinUsersDTO) {
        List<User> usersPs = userRepository.saveUser(joinUsersDTO.toEntity());
        return new UserResponse.joinedUsersDTO(usersPs);
    }
}
