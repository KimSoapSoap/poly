package org.mtcoding.poly.user;


import lombok.RequiredArgsConstructor;
import org.mtcoding.poly.core.exception.api.ExceptionApi403;
import org.mtcoding.poly.core.exception.api.ExceptionApi404;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse.joinedUserDTO join(UserRequest.JoinUserDTO joinDTO) {
       User userPs = userRepository.saveUser(joinDTO.toEntity());
        return new UserResponse.joinedUserDTO(userPs);
    }

    public UserResponse.searchedUserDTO findUser(int id) {
        User userPs = userRepository.findUserById(id);
        if(userPs == null) {
            throw new ExceptionApi404("id: " + id + " 존재하지 않는 회원입니다.");
        }
        return new UserResponse.searchedUserDTO(userPs);
    }

    @Transactional
    public UserResponse.searchedUserDTO updateUser(int id, UserRequest.ModifyUserDTO modifyUserDTO) {
        User userPs = userRepository.findUserById(id);
        if(userPs == null) {
            throw new ExceptionApi404("id: " + id + " 존재하지 않는 회원입니다.");
        }

        if(id != modifyUserDTO.getId()) {
            throw new ExceptionApi403("수정 권한이 없습니다.");
        }
        userPs.changeName(modifyUserDTO.getName());

        return new UserResponse.searchedUserDTO(userPs);
    }
}
