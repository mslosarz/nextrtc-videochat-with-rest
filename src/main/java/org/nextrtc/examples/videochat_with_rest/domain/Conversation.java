package org.nextrtc.examples.videochat_with_rest.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static org.joda.time.DateTime.now;

@Entity
@Table(name = "Conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "conversation_id")
    private int id;

    @Column(name = "conversation_name")
    private String conversationName;

    @Column(name = "created")
    private Date created;

    @Column(name = "destroyed")
    private Date destroyed;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Connection> connections;

    @Deprecated
    Conversation() {
    }

    public Conversation(String conversationName) {
        this.conversationName = conversationName;
        created = now().toDate();
    }

    @Override
    public String toString() {
        return String.format("(%s)[%s - %s]", conversationName, created, destroyed);
    }

    public void destroy() {
        destroyed = now().toDate();
        connections.stream()
                .filter(Connection::isClosed)
                .forEach(Connection::close);
    }

    public void join(Member member) {
        connections.add(new Connection(this, member));
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Conversation)) return false;
        final Conversation other = (Conversation) o;
        return other.id == id;
    }

    public int hashCode() {
        return id;
    }

    public Set<Connection> getConnections() {
        return connections;
    }
}
