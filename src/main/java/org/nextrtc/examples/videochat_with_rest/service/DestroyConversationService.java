package org.nextrtc.examples.videochat_with_rest.service;

import org.nextrtc.examples.videochat_with_rest.repo.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DestroyConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    public void execute(String roomName) {
        conversationRepository.getByConversationName(roomName).destroy();
    }
}
