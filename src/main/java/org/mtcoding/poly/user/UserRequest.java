package org.mtcoding.poly.user;


import lombok.Getter;
import java.util.List;
import java.util.ArrayList;

public class UserRequest {

    @Getter
    public static class JoinUsersDTO {
        public List<UserDTO> users = new ArrayList<>();

        public JoinUsersDTO(List<User> users) {
            for(User user : users) {
                this.users.add(new UserDTO(user));
            }
        }

        public List<User> toEntity() {
            List<User> users = new ArrayList<>();
            for(UserDTO userDTO : this.users) {
                users.add(User.builder().name(userDTO.getName()).build());
            }
            return users;
        }

        @Getter
        public class UserDTO {
            private String name;

            public UserDTO(User user) {
                this.name = user.getName();
            }

        }

    }

    @Getter
    public static class modifyUserDTO {
        private Integer id;
        private String name;

        public modifyUserDTO(User user) {
            this.id = user.getId();
            this.name = user.getName();
        }
    }
}
