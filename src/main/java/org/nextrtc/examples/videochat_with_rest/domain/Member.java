package org.nextrtc.examples.videochat_with_rest.domain;

import org.nextrtc.examples.videochat_with_rest.domain.history.Call;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

import static org.joda.time.DateTime.now;

@Entity
@Table(name = "Members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private int id;

    @Column(name = "member_rtc_id")
    private String rtcId;

    @Column(name = "connected")
    private Date connected;

    @Column(name = "disconnected")
    private Date disconnected;

    @Column(name = "left_reason")
    private String leftReason;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Connection connection;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * for hibernate only
     */
    @Deprecated
    Member() {
    }

    public Member(String memberId, User user) {
        this.rtcId = memberId;
        this.user = user;
        this.connected = now().toDate();
    }

    public void disconnectWithReason(Optional<String> reason) {
        disconnected = now().toDate();
        reason.ifPresent(this::setLeftReason);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)[%s - %s]", id, rtcId, connected, disconnected);
    }

    public void leaves(Conversation conversation) {
        if (connection.isFor(conversation)) {
            connection.close();
        }
    }

    private void setLeftReason(String leftReason) {
        this.leftReason = leftReason;
    }

    public int startedBefore(Member p) {
        return connected.compareTo(p.connected);
    }

    public Call toCall() {
//        List<Member> members = connection.getConversationMembers().stream()
//                .filter(m -> !m.equals(this))
//                .map(m -> m.rtcId)
//                .collect(toList());
//        return new Call();
        return null;
    }

}
