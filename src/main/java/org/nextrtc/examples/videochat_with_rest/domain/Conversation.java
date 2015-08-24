package org.nextrtc.examples.videochat_with_rest.domain;

import java.util.Set;

import javax.persistence.*;

import org.joda.time.DateTime;

@Entity
// @NamedQueries({ //
// @NamedQuery(name = "membersSize", query = "select count(c.members) from Conversation c where c.rtcId = :rtcId") //
// })
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String roomName;

    private DateTime startDate;

    private DateTime endDate;

    @OneToMany
    private Set<Member> members;

}
