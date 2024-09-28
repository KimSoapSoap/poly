package org.mtcoding.poly.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public List<User> saveUser(List<User> users) {
        System.out.println("전:" + users);
        for(User user: users) {
            em.persist(user);
        }
        System.out.println("후: " + users);
        return users;
    }
}
