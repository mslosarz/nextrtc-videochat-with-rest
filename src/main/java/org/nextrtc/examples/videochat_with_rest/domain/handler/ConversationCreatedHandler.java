package org.nextrtc.examples.videochat_with_rest.domain.handler;

import lombok.extern.log4j.Log4j;
import org.nextrtc.examples.videochat_with_rest.domain.Conversation;
import org.nextrtc.examples.videochat_with_rest.domain.handler.common.ConversationHandler;
import org.nextrtc.examples.videochat_with_rest.repo.ConversationRepository;
import org.nextrtc.signalingserver.api.annotation.NextRTCEventListener;
import org.nextrtc.signalingserver.api.dto.NextRTCConversation;
import org.nextrtc.signalingserver.api.dto.NextRTCEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.nextrtc.signalingserver.api.NextRTCEvents.CONVERSATION_CREATED;

@Log4j
@Component
@NextRTCEventListener(CONVERSATION_CREATED)
public class ConversationCreatedHandler extends ConversationHandler {

    @Autowired
    private ConversationRepository repo;

    @Override
    protected void handleEvent(NextRTCConversation rtcConversation, NextRTCEvent event) {
        log.info("Created conversation: " + repo.save(new Conversation(rtcConversation.getId())));
    }
}
