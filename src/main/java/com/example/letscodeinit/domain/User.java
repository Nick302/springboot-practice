package com.example.letscodeinit.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private boolean active; //признак активности

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER) //не нужно самому таблицу на енам делать ,еагер лучше когда мало данных - но потребляет память
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))//поле будет в отдельной таблице которое мы не описывали , которая будет соедениться с текущей по user_id
    @Enumerated(EnumType.STRING) //енам хранить в виде строки
    private Set<Role> roles; // ролевая админ юзер и тд

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}