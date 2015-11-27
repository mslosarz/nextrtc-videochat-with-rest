/* Copyright 2015 Sabre Holdings */
package org.nextrtc.examples.videochat_with_rest.domain.handler;

import org.nextrtc.examples.videochat_with_rest.repo.MemberRepository;
import org.nextrtc.signalingserver.api.annotation.NextRTCEventListener;
import org.nextrtc.signalingserver.api.dto.NextRTCEvent;
import org.nextrtc.signalingserver.api.dto.NextRTCMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.nextrtc.signalingserver.api.NextRTCEvents.MEMBER_JOINED;

@Component
@NextRTCEventListener(MEMBER_JOINED)
public class MemberJoinHandler extends FromMemberHandler {

    @Autowired
    private MemberRepository repo;


    @Override
    protected void handleEvent(NextRTCMember from, NextRTCEvent event) {

    }
}
