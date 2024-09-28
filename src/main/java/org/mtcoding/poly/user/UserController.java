package org.mtcoding.poly.user;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@Valid @RequestBody List<User> users, Errors erros) {
        // 어떤 형태로 올지 몰라서 응답 예상 모양과 같은
        // [{"name": "a"}, {"name":"b" },{"name": "c}]  이런 배열 형태로 온다고 가정하고 List<User>로 받아서 DTO를 만들었습니다.
        UserResponse.joinedUsersDTO joinedUsersDTO = userService.join(new UserRequest.JoinUsersDTO(users));
        return ResponseEntity.ok(joinedUsersDTO.getUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findUser(@PathVariable int id) {
        UserResponse.UserDTO userDTO = userService.findUser(id);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @Valid @RequestBody UserRequest.ModifyUserDTO modifyUserDTO, Errors erros) {
        UserResponse.UserDTO userDTO = userService.updateUser(id, modifyUserDTO);
        return ResponseEntity.ok(userDTO);
    }
}
