package org.mtcoding.poly.user;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@Getter
@Table(name = "user_tb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank
    private String name;


    @Builder
    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void changeName(String name) {
        this.name = name;
    }

}
