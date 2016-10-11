package org.nextrtc.examples.videochat_with_rest.domain.handler;

import org.apache.log4j.Logger;
import org.nextrtc.examples.videochat_with_rest.domain.handler.common.FromMemberHandler;
import org.nextrtc.examples.videochat_with_rest.service.SessionClosedService;
import org.nextrtc.signalingserver.api.annotation.NextRTCEventListener;
import org.nextrtc.signalingserver.api.dto.NextRTCEvent;
import org.nextrtc.signalingserver.api.dto.NextRTCMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.nextrtc.signalingserver.api.NextRTCEvents.SESSION_CLOSED;

@Component
@NextRTCEventListener(SESSION_CLOSED)
public class SessionClosedHandler extends FromMemberHandler {

    private static final Logger log = Logger.getLogger(SessionClosedHandler.class);
    @Autowired
    private SessionClosedService service;

    @Override
    protected void handleEvent(NextRTCMember from, NextRTCEvent event) {
        service.execute(from.getId(), event.reason());
    }

}
