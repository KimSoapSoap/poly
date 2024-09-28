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
        for(User user : users) {
            em.persist(user);
        }
        return users;
    }

    public User findUserById(int id) {
        return em.find(User.class, id);
    }

}
