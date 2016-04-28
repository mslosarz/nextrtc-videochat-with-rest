package org.nextrtc.examples.videochat_with_rest.repo;

import org.nextrtc.examples.videochat_with_rest.domain.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

    Conversation getByConversationName(@Param("conversationName") String conversationName);
}
