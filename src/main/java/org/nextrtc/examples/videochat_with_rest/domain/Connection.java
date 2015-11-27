/* Copyright 2015 Sabre Holdings */
package org.nextrtc.examples.videochat_with_rest.domain;

import lombok.Getter;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Connection {

    @Getter
    private DateTime start;
    @Getter
    private DateTime end;

    @OneToOne
    private Member member;

    @ManyToOne
    private Conversation conversation;
}
