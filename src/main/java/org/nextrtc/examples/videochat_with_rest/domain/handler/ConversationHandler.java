/* Copyright 2015 Sabre Holdings */
package org.nextrtc.examples.videochat_with_rest.domain.handler;

import org.nextrtc.signalingserver.api.dto.NextRTCConversation;
import org.nextrtc.signalingserver.api.dto.NextRTCEvent;
import org.nextrtc.signalingserver.api.dto.NextRTCMember;

public abstract class ConversationHandler extends FromMemberHandler {

    protected void handleEvent(NextRTCMember from, NextRTCEvent event){
        event.conversation().ifPresent(conversation -> handleEvent(from, conversation, event));
    }

    protected abstract void handleEvent(NextRTCMember from, NextRTCConversation conversation, NextRTCEvent event);
}
