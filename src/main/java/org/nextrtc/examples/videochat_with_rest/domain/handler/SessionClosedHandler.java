package org.nextrtc.examples.videochat_with_rest.domain.handler;

import lombok.extern.log4j.Log4j;
import org.nextrtc.examples.videochat_with_rest.domain.handler.common.FromMemberHandler;
import org.nextrtc.examples.videochat_with_rest.service.SessionClosedService;
import org.nextrtc.signalingserver.api.annotation.NextRTCEventListener;
import org.nextrtc.signalingserver.api.dto.NextRTCEvent;
import org.nextrtc.signalingserver.api.dto.NextRTCMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.nextrtc.signalingserver.api.NextRTCEvents.SESSION_CLOSED;

@Log4j
@Component
@NextRTCEventListener(SESSION_CLOSED)
public class SessionClosedHandler extends FromMemberHandler {

    @Autowired
    private SessionClosedService service;

    @Override
    protected void handleEvent(NextRTCMember from, NextRTCEvent event) {
        service.execute(from.getId(), event.reason());
    }

}
