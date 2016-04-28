package org.nextrtc.examples.videochat_with_rest.service;

import org.nextrtc.examples.videochat_with_rest.domain.Conversation;
import org.nextrtc.examples.videochat_with_rest.domain.Member;
import org.nextrtc.examples.videochat_with_rest.repo.ConversationRepository;
import org.nextrtc.examples.videochat_with_rest.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MemberLeftService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    public void execute(String memberId, String conversationId) {
        Member member = memberRepository.getByRtcId(memberId);
        Conversation conversation = conversationRepository.getByConversationName(conversationId);

        member.leaves(conversation);
    }
}
