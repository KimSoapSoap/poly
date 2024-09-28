package org.mtcoding.poly.user;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {

    @Getter
    public static class UserDTO {
        private Integer id;
        private String name;


        public UserDTO(User user) {
            this.id = user.getId();
            this.name = user.getName();
        }
    }

    @Getter
    public static class joinedUsersDTO {
        private List<UserDTO> users = new ArrayList<>();

        public joinedUsersDTO(List<User> usersPS) {
            for(User user : usersPS) {
                users.add(new UserDTO(user));
            }
        }

        @Getter
        public class UserDTO {
            private Integer id;

            public UserDTO(User user) {
                this.id = user.getId();
            }
        }
    }

}
