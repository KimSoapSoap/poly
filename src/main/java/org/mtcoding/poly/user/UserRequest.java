package org.mtcoding.poly.user;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequest {

    @Getter
    @NoArgsConstructor
    public static class JoinUserDTO {
        @NotBlank(message = "이름을 입력해주세요")
        private String name;

        public User toEntity() {
            return User.builder().name(name).build();
        }

    }

    @Getter
    @NoArgsConstructor
    public static class ModifyUserDTO {

        @NotNull
        private Integer id;
        @NotBlank(message = "이름을 입력해주세요")
        private String name;

    }
}
