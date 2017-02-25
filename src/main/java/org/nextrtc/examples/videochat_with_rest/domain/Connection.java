package org.nextrtc.examples.videochat_with_rest.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.joda.time.DateTime.now;

@Entity
@Table(name = "Connections")
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "connection_id")
    private int id;

    @JoinColumn(name = "begin")
    private Date begin;

    @JoinColumn(name = "closed")
    private Date closed;

    @JoinColumn(name = "took")
    private Long took;

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
        begin = now().toDate();
    }

    public boolean isClosed() {
        return closed != null;
    }

    public void close() {
        closed = now().toDate();
        took = new Interval(new DateTime(begin), new DateTime(closed)).toDurationMillis();
    }

    public boolean isFor(Conversation conversation) {
        return new EqualsBuilder()
                .append(this.conversation, conversation)
                .isEquals();
    }

    public List<Member> getConversationMembers() {
        return conversation.getConnections().stream().map(c -> c.member).collect(toList());
    }

    public Date getBegin() {
        return begin;
    }

    public long getDuration() {
        if (took != null) {
            return took;
        }
        return new Interval(new DateTime(begin), DateTime.now()).toDurationMillis();
    }
}
