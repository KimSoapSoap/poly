package org.mtcoding.poly.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EntityManager em;

    @BeforeEach
    void setUp() throws Exception {
        User user = User.builder().name("ksh").build();
        em.persist(user);
        em.flush();
        em.clear();
    }


    @DisplayName("유저 등록 테스트")
    @Test
    public void saveUser_Test() throws Exception {
        //given
        List<User> users = new ArrayList<>();
        users.add(User.builder().name("ssar").build());
        users.add(User.builder().name("love").build());
        users.add(User.builder().name("cos").build());

        String usersJson = objectMapper.writeValueAsString(users);

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .header("User-Agent", "유저 등록 테스트")
                .content(usersJson))

        //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[1].id").value(3))
                .andExpect(jsonPath("$[2].id").value(4));
    }


    @DisplayName("유저 조회 테스트")
    @Transactional(readOnly = true)
    @Test
    public void findUser_Test() throws Exception {
        //given
        int id = 1;

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}",id)
                .header("User-Agent", "유저 조회 테스트"))


        //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("ksh"));

    }


    @DisplayName("유저 수정 테스트")
    @Test
    public void chageUser_Test() throws Exception {
        //given
        int id = 1;
        User user = User.builder().id(id).name("hapssar").build();

        String userJson = objectMapper.writeValueAsString(user);

        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("User-Agent", "유저 수정 테스트")
                .content(userJson))


        //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("hapssar"));


    }


}