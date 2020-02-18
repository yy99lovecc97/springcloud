package com.lovecc.uaaservice.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Role implements GrantedAuthority {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    private String name;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
