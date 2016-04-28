package org.nextrtc.examples.videochat_with_rest.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role = "USER";

    @Column(name = "email")
    private String email;

    @Column(name = "confirmed")
    private boolean confirmed = true; // TODO: change to false and add email service

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Member> conversationMember;

    @Deprecated
    User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
