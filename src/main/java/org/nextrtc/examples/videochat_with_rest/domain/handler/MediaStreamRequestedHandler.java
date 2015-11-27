/* Copyright 2015 Sabre Holdings */
package org.nextrtc.examples.videochat_with_rest.domain.handler;

import org.nextrtc.signalingserver.api.NextRTCHandler;
import org.nextrtc.signalingserver.api.annotation.NextRTCEventListener;
import org.nextrtc.signalingserver.api.dto.NextRTCEvent;
import org.springframework.stereotype.Component;

import static org.nextrtc.signalingserver.api.NextRTCEvents.MEDIA_LOCAL_STREAM_REQUESTED;

@Component
@NextRTCEventListener(MEDIA_LOCAL_STREAM_REQUESTED)
public class MediaStreamRequestedHandler implements NextRTCHandler {

    @Override
    public void handleEvent(NextRTCEvent event) {

    }
}
