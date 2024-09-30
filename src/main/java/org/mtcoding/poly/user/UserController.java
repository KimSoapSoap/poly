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
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserRequest.JoinUserDTO joinDTO, Errors erros) {
        UserResponse.joinedUserDTO joinedDTO = userService.join(joinDTO);
        return ResponseEntity.ok(joinedDTO);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findUser(@PathVariable int id) {
        UserResponse.searchedUserDTO searchedUserDTO = userService.findUser(id);
        return ResponseEntity.ok(searchedUserDTO);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @Valid @RequestBody UserRequest.ModifyUserDTO modifyUserDTO, Errors erros) {
        UserResponse.searchedUserDTO searchedUserDTO = userService.updateUser(id, modifyUserDTO);
        return ResponseEntity.ok(searchedUserDTO);
    }
}
