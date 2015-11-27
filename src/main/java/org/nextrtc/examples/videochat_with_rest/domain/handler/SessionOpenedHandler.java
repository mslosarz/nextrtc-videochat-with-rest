package org.nextrtc.examples.videochat_with_rest.domain.handler;

import lombok.extern.log4j.Log4j;
import org.nextrtc.examples.videochat_with_rest.domain.Member;
import org.nextrtc.examples.videochat_with_rest.repo.MemberRepository;
import org.nextrtc.signalingserver.api.annotation.NextRTCEventListener;
import org.nextrtc.signalingserver.api.dto.NextRTCEvent;
import org.nextrtc.signalingserver.api.dto.NextRTCMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.nextrtc.signalingserver.api.NextRTCEvents.SESSION_OPENED;

@Log4j
@Component
@NextRTCEventListener(SESSION_OPENED)
public class SessionOpenedHandler extends FromMemberHandler {

    @Autowired
    private MemberRepository repo;

    @Override
    @Transactional
    protected void handleEvent(NextRTCMember from, NextRTCEvent event) {
        repo.save(new Member(from));
        log.info(repo.findByRtcId(from.getId()));
    }

}
