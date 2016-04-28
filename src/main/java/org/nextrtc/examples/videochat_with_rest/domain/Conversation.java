package org.nextrtc.examples.videochat_with_rest.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Set;

import static org.joda.time.DateTime.now;

@Entity
@Table(name = "Conversations")
@EqualsAndHashCode(exclude = {"connections"})
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "conversation_id")
    private int id;

    @Column(name = "conversation_name")
    private String conversationName;

    @Getter
    @Column(name = "created")
    private DateTime created;

    @Getter
    @Column(name = "destroyed")
    private DateTime destroyed;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Connection> connections;

    @Deprecated
    Conversation() {
    }

    public Conversation(String conversationName) {
        this.conversationName = conversationName;
        created = now();
    }

    @Override
    public String toString() {
        return String.format("(%s)[%s - %s]", conversationName, created, destroyed);
    }

    public void destroy() {
        destroyed = now();
        connections.stream()
                .filter(Connection::isClosed)
                .forEach(Connection::close);
    }

    public void join(Member member) {
        connections.add(new Connection(this, member));
    }
}
