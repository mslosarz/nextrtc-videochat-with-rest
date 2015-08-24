package org.nextrtc.examples.videochat_with_rest.domain;

import javax.persistence.*;

import lombok.Getter;

import org.joda.time.DateTime;
import org.nextrtc.signalingserver.api.NextRTCEvent;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter
    private DateTime created = DateTime.now();

    @Getter
    private DateTime finished;

    private String login;

    private String rtcId;

    @ManyToOne
    private Conversation conv;

    /**
     * for hibernate only
     */
    @Deprecated
    Member() {
    }

    public Member(NextRTCEvent event) {
        event.getSessionId().ifPresent(id -> this.rtcId = id);
    }

    public boolean hasConnection() {
        return rtcId != null;
    }

    public boolean hasConversation() {
        return hasConnection() && conv != null;
    }

    public void markEnd() {
        finished = DateTime.now();
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)[%s - %s]", id, rtcId, created, finished);
    }

}
