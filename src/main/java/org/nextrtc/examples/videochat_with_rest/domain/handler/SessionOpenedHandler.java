package org.nextrtc.examples.videochat_with_rest.domain.handler;

import static org.nextrtc.signalingserver.api.NextRTCEvents.SESSION_OPENED;
import lombok.extern.log4j.Log4j;

import org.nextrtc.examples.videochat_with_rest.domain.Member;
import org.nextrtc.examples.videochat_with_rest.repo.MemberRepository;
import org.nextrtc.signalingserver.api.NextRTCEvent;
import org.nextrtc.signalingserver.api.NextRTCHandler;
import org.nextrtc.signalingserver.api.annotation.NextRTCEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Log4j
@Component
@NextRTCEventListener(SESSION_OPENED)
public class SessionOpenedHandler implements NextRTCHandler {

    @Autowired
    private MemberRepository repo;

    @Override
    @Transactional
    public void handleEvent(NextRTCEvent event) {
        // save information about new connected person
        repo.save(new Member(event));
        event.getSessionId().ifPresent(c -> log.info(repo.findByRtcId(c)));
    }

}
