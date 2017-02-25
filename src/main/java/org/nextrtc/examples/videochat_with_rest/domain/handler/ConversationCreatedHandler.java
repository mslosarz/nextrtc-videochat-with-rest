package org.nextrtc.examples.videochat_with_rest.domain.handler;

import org.apache.log4j.Logger;
import org.nextrtc.examples.videochat_with_rest.domain.Conversation;
import org.nextrtc.examples.videochat_with_rest.domain.handler.common.ConversationHandler;
import org.nextrtc.examples.videochat_with_rest.repo.ConversationRepository;
import org.nextrtc.signalingserver.api.annotation.NextRTCEventListener;
import org.nextrtc.signalingserver.api.dto.NextRTCConversation;
import org.nextrtc.signalingserver.api.dto.NextRTCEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.nextrtc.signalingserver.api.NextRTCEvents.CONVERSATION_CREATED;

@Component
@NextRTCEventListener(CONVERSATION_CREATED)
public class ConversationCreatedHandler extends ConversationHandler {

    private static final Logger log = Logger.getLogger(ConversationCreatedHandler.class);
    @Autowired
    private ConversationRepository repo;

    @Override
    protected void handleEvent(NextRTCConversation rtcConversation, NextRTCEvent event) {
        Conversation conversation = repo.getByConversationName(rtcConversation.getId());
        if (conversation == null) {
            log.info("Created conversation: " + repo.save(new Conversation(rtcConversation.getId())));
        }
    }
}
