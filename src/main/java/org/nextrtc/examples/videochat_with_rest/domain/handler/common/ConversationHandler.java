package org.nextrtc.examples.videochat_with_rest.domain.handler.common;

import org.nextrtc.signalingserver.api.NextRTCHandler;
import org.nextrtc.signalingserver.api.dto.NextRTCConversation;
import org.nextrtc.signalingserver.api.dto.NextRTCEvent;

public abstract class ConversationHandler implements NextRTCHandler {

    @Override
    public final void handleEvent(NextRTCEvent event) {
        event.conversation().ifPresent(conversation -> handleEvent(conversation, event));
    }

    protected abstract void handleEvent(NextRTCConversation conversation, NextRTCEvent event);
}
