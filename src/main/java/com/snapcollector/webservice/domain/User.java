package com.snapcollector.webservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column
    private String gender;

    @Column
    private String age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @Builder
    public User(String gender, String email, String age, Role role) {
        this.gender = gender;
        this.email = email;
        this.age = age;
        this.role = role;
    }

    public User update(String gender, String age) {
        this.gender = gender;
        this.age = age;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
