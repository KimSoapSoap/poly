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
        UserResponse.joinedUsersDTO joinedUsersDTO = userService.join(new UserRequest.JoinUsersDTO(users));
        return ResponseEntity.ok(joinedUsersDTO.getUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findUser(@PathVariable int id) {
        UserResponse.UserDTO userDTO = userService.findUser(id);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @Valid @RequestBody User user, Errors erros) {
        UserResponse.UserDTO userDTO = userService.updateUser(id, user);
        return ResponseEntity.ok(userDTO);
    }
}
