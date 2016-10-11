package org.nextrtc.examples.videochat_with_rest.domain;

import org.joda.time.DateTime;

import javax.persistence.*;
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
    private DateTime created;

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

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Conversation)) return false;
        final Conversation other = (Conversation) o;
        if (!other.canEqual(this)) return false;
        if (this.id != other.id) return false;
        final Object this$conversationName = this.conversationName;
        final Object other$conversationName = other.conversationName;
        if (this$conversationName == null ? other$conversationName != null : !this$conversationName.equals(other$conversationName))
            return false;
        final Object this$created = this.created;
        final Object other$created = other.created;
        if (this$created == null ? other$created != null : !this$created.equals(other$created)) return false;
        final Object this$destroyed = this.destroyed;
        final Object other$destroyed = other.destroyed;
        return this$destroyed == null ? other$destroyed == null : this$destroyed.equals(other$destroyed);
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.id;
        final Object $conversationName = this.conversationName;
        result = result * PRIME + ($conversationName == null ? 0 : $conversationName.hashCode());
        final Object $created = this.created;
        result = result * PRIME + ($created == null ? 0 : $created.hashCode());
        final Object $destroyed = this.destroyed;
        result = result * PRIME + ($destroyed == null ? 0 : $destroyed.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Conversation;
    }

    public DateTime getCreated() {
        return this.created;
    }

    public DateTime getDestroyed() {
        return this.destroyed;
    }
}
