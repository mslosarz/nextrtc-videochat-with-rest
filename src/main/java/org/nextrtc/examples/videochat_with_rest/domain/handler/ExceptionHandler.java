package org.nextrtc.examples.videochat_with_rest.domain.handler;

import lombok.extern.log4j.Log4j;
import org.nextrtc.signalingserver.api.NextRTCHandler;
import org.nextrtc.signalingserver.api.annotation.NextRTCEventListener;
import org.nextrtc.signalingserver.api.dto.NextRTCEvent;
import org.springframework.stereotype.Component;

import static org.nextrtc.signalingserver.api.NextRTCEvents.UNEXPECTED_SITUATION;

@Log4j
@Component
@NextRTCEventListener(UNEXPECTED_SITUATION)
public class ExceptionHandler implements NextRTCHandler {

    @Override
    public void handleEvent(NextRTCEvent nextRTCEvent) {
        log.error(nextRTCEvent);
    }
}
