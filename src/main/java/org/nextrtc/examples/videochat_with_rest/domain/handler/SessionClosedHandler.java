package org.nextrtc.examples.videochat_with_rest.domain.handler;

import org.nextrtc.examples.videochat_with_rest.repo.MemberRepository;
import org.nextrtc.signalingserver.api.annotation.NextRTCEventListener;
import org.nextrtc.signalingserver.api.dto.NextRTCEvent;
import org.nextrtc.signalingserver.api.dto.NextRTCMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.nextrtc.signalingserver.api.NextRTCEvents.SESSION_CLOSED;

@Component
@NextRTCEventListener(SESSION_CLOSED)
public class SessionClosedHandler extends FromMemberHandler {

    @Autowired
    private MemberRepository repo;

    @Override
    @Transactional
    protected void handleEvent(NextRTCMember from, NextRTCEvent event) {
        repo.findByRtcId(from.getId()).ifPresent(member ->
                member.markEnd(event.reason())
        );
    }

}