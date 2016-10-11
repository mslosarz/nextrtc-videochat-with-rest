package org.nextrtc.examples.videochat_with_rest.domain.handler;

import org.apache.log4j.Logger;
import org.nextrtc.signalingserver.api.NextRTCHandler;
import org.nextrtc.signalingserver.api.annotation.NextRTCEventListener;
import org.nextrtc.signalingserver.api.dto.NextRTCEvent;
import org.springframework.stereotype.Component;

import static org.nextrtc.signalingserver.api.NextRTCEvents.UNEXPECTED_SITUATION;

@Component
@NextRTCEventListener(UNEXPECTED_SITUATION)
public class ExceptionHandler implements NextRTCHandler {

    private static final Logger log = Logger.getLogger(ExceptionHandler.class);

    @Override
    public void handleEvent(NextRTCEvent nextRTCEvent) {
        log.error(nextRTCEvent);
    }
}
