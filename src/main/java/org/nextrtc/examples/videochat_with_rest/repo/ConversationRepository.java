package org.nextrtc.examples.videochat_with_rest.repo;

import org.nextrtc.examples.videochat_with_rest.domain.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

}
