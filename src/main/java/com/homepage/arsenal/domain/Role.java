package com.homepage.arsenal.domain;

import com.homepage.arsenal.enums.RoleType;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
