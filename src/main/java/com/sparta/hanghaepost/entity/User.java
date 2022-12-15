package com.sparta.hanghaepost.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "users") // user 예약어
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{4,12}",
            message = "4자 ~ 10자 사이 빈 문자열, null 허용 X, 대문자를 허용하지 않으며, 소문자와 숫자의 조합만 허용")
    private String username;

    @Column(nullable = false)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,15}",
            message = "8자 ~ 15자 사이 빈 문자열, null 허용 X, 소문자와 대문자" +
                    " 숫자, 특수문자의 조합만 허용")
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> postList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments;

    public User(String username, String password, UserRoleEnum role){
        this.username = username;
        this.password = password;
        this.role = role;
    }





}
