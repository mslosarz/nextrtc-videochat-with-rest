package org.nextrtc.examples.videochat_with_rest.domain;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;

@Entity
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String roomName;

    @OneToOne(cascade = PERSIST)
    private CreationDetails created;

//    @OneToOne(cascade = CascadeType.)
    private DestroyDetails ended;

    @OneToMany
    private Set<Connection> connections;

    Conversation(){}

    public Conversation(String roomName){
        this.roomName = roomName;
        created = new CreationDetails();
    }

    @Override
    public String toString() {
        return String.format("(%s)[%s - %s]", roomName, created, ended);
    }

    public void markEnd() {
        ended = new DestroyDetails();
    }
}
