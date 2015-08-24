package org.nextrtc.examples.videochat_with_rest.domain.handler;

import static org.nextrtc.signalingserver.api.NextRTCEvents.CONVERSATION_CREATED;

import org.nextrtc.examples.videochat_with_rest.repo.ConversationRepository;
import org.nextrtc.signalingserver.api.NextRTCEvent;
import org.nextrtc.signalingserver.api.NextRTCHandler;
import org.nextrtc.signalingserver.api.annotation.NextRTCEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NextRTCEventListener(CONVERSATION_CREATED)
public class ConversationCreateHandler implements NextRTCHandler {

    @Autowired
    private ConversationRepository repo;

    @Override
    public void handleEvent(NextRTCEvent event) {

    }

}
