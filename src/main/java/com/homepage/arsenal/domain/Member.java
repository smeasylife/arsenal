package com.homepage.arsenal.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String nickname;

    @OneToMany(mappedBy = "writer")
    private List<Post> posts = new ArrayList<>();

    private String username;

    private String password;

    @OneToMany(mappedBy = "writer")
    private List<Comment> comments = new ArrayList<>();
}
