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
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserRequest.JoinUsersDTO joinUsersDTO, Errors erros) {
        System.out.println("joinUsersDTO = " + joinUsersDTO);
        UserResponse.joinedUsersDTO joinedUsersDTO = userService.join(joinUsersDTO);
        return ResponseEntity.ok(joinedUsersDTO);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findUser(@PathVariable Integer id) {
        return ResponseEntity.ok("get");
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors erros) {
        return ResponseEntity.ok("put");
    }
}
