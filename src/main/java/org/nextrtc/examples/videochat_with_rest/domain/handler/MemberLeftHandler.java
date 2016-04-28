package org.nextrtc.examples.videochat_with_rest.domain.handler;

import org.nextrtc.examples.videochat_with_rest.domain.handler.common.ConversationHandler;
import org.nextrtc.examples.videochat_with_rest.service.MemberLeftService;
import org.nextrtc.signalingserver.api.annotation.NextRTCEventListener;
import org.nextrtc.signalingserver.api.dto.NextRTCConversation;
import org.nextrtc.signalingserver.api.dto.NextRTCEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.nextrtc.signalingserver.api.NextRTCEvents.MEMBER_LEFT;

@Component
@NextRTCEventListener(MEMBER_LEFT)
public class MemberLeftHandler extends ConversationHandler {

    @Autowired
    private MemberLeftService service;

    @Override
    protected void handleEvent(NextRTCConversation conversation, NextRTCEvent event) {
        event.from().ifPresent(member ->
                service.execute(member.getId(), conversation.getId())
        );
    }
}
