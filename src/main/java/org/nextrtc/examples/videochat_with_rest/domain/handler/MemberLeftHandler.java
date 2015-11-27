/* Copyright 2015 Sabre Holdings */
package org.nextrtc.examples.videochat_with_rest.domain.handler;

import org.nextrtc.examples.videochat_with_rest.repo.ConversationRepository;
import org.nextrtc.signalingserver.api.annotation.NextRTCEventListener;
import org.nextrtc.signalingserver.api.dto.NextRTCConversation;
import org.nextrtc.signalingserver.api.dto.NextRTCEvent;
import org.nextrtc.signalingserver.api.dto.NextRTCMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.nextrtc.signalingserver.api.NextRTCEvents.MEMBER_LEFT;

@Component
@NextRTCEventListener(MEMBER_LEFT)
public class MemberLeftHandler extends ConversationHandler {

    @Autowired
    private ConversationRepository repo;

    @Override
    protected void handleEvent(NextRTCMember from, NextRTCConversation conversation, NextRTCEvent event) {


    }
}
