package org.mtcoding.poly.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mtcoding.poly.core.exception.api.ExceptionApi403;
import org.mtcoding.poly.core.exception.api.ExceptionApi404;
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

    public UserResponse.UserDTO findUser(int id) {
        User userPs = userRepository.findUserById(id);
        if(userPs == null) {
            throw new ExceptionApi404("존재하지 않는 회원입니다.");
        }
        return new UserResponse.UserDTO(userPs);
    }

    @Transactional
    public UserResponse.UserDTO updateUser(int id, User user) {
        User userPs = userRepository.findUserById(id);
        if(userPs == null) {
            throw new ExceptionApi404("존재하지 않는 회원입니다.");
        }

        if(id != user.getId()) {
            throw new ExceptionApi403("수정 권한이 없습니다.");
        }

        userPs.changeName(user.getName());

        return new UserResponse.UserDTO(userPs);
    }
}
