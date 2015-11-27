package org.nextrtc.examples.videochat_with_rest.domain;

import lombok.Getter;
import org.nextrtc.signalingserver.api.dto.NextRTCMember;

import javax.persistence.*;
import java.util.Optional;

import static javax.persistence.CascadeType.PERSIST;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String rtcId;

    @Getter
    @OneToOne(cascade = PERSIST)
    private CreationDetails created;

    @OneToOne
    private DestroyDetails destroyed;

    @OneToOne
    private Connection connection;

    /**
     * for hibernate only
     */
    @Deprecated
    Member() {
    }

    public Member(NextRTCMember from) {
        this.rtcId = from.getId();
        this.created = new CreationDetails();
    }

    private boolean isAttached() {
        return rtcId != null;
    }

    public boolean hasConnection() {
        return isAttached() && connection != null;
    }

    public void markEnd(Optional<String> cause) {
        if (destroyed == null) {
            destroyed = new DestroyDetails(cause);
        }
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)[%s - %s]", id, rtcId, created, destroyed);
    }

}
