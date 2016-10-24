package org.nextrtc.examples.videochat_with_rest.domain;

import org.joda.time.DateTime;

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

    public DateTime getConnected() {
        return new DateTime(this.connected);
    }

    public DateTime getDisconnected() {
        return new DateTime(this.disconnected);
    }

    public String getLeftReason() {
        return this.leftReason;
    }

    private void setLeftReason(String leftReason) {
        this.leftReason = leftReason;
    }
}
