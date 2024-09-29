package org.mtcoding.poly.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public User saveUser(User user) {
        em.persist(user);
        return user;
    }

    public User findUserById(int id) {
        return em.find(User.class, id);
    }

}
