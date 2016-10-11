package org.nextrtc.examples.videochat_with_rest.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import javax.persistence.*;

import static org.joda.time.DateTime.now;

@Entity
@Table(name = "Connections")
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "connection_id")
    private int id;

    @JoinColumn(name = "begin")
    private DateTime begin;

    @JoinColumn(name = "closed")
    private DateTime closed;

    @JoinColumn(name = "took")
    private long took;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @Deprecated
    Connection() {
    }

    Connection(Conversation conversation, Member member) {
        this.conversation = conversation;
        this.member = member;
        begin = now();
    }

    public boolean isClosed() {
        return closed != null;
    }

    public void close() {
        closed = now();
        took = new Interval(begin, closed).toDurationMillis();
    }

    public boolean isFor(Conversation conversation) {
        return new EqualsBuilder()
                .append(this.conversation, conversation)
                .isEquals();
    }

    public DateTime getBegin() {
        return this.begin;
    }

    public DateTime getClosed() {
        return this.closed;
    }

    public long getTook() {
        return this.took;
    }
}
