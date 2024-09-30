package org.mtcoding.poly.user;

import lombok.Getter;

public class UserResponse {

    @Getter
    public static class searchedUserDTO {
        private Integer id;
        private String name;


        public searchedUserDTO(User user) {
            this.id = user.getId();
            this.name = user.getName();
        }
    }

    @Getter
    public static class joinedUserDTO {
        private Integer id;

        public joinedUserDTO(User user) {
            this.id = user.getId();
        }
    }


}
