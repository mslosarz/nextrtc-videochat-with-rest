package org.nextrtc.examples.videochat_with_rest.service;

import lombok.extern.log4j.Log4j;
import org.nextrtc.examples.videochat_with_rest.domain.Member;
import org.nextrtc.examples.videochat_with_rest.domain.User;
import org.nextrtc.examples.videochat_with_rest.repo.MemberRepository;
import org.nextrtc.examples.videochat_with_rest.repo.UserRepository;
import org.nextrtc.signalingserver.api.dto.NextRTCMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.websocket.Session;

@Log4j
@Service
@Transactional
@PreAuthorize(value = "USER")
public class SessionOpenedService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserRepository userRepository;

    public void execute(NextRTCMember member) {
        User user = getAuthenticatedUser(member.getSession());

        throwExceptionWhenUserWasNotFound(user);

        createConversationMemberFor(member.getId(), user);
    }

    private void createConversationMemberFor(String memberId, User user) {
        log.info("Created member: " + memberRepository.save(new Member(memberId, user)));
    }

    private void throwExceptionWhenUserWasNotFound(User user) {
        if (user == null) {
            throw new RuntimeException("User isn't authenticated!");
        }
    }

    private User getAuthenticatedUser(Session session) {
        return userRepository.getByUsername(session.getUserPrincipal().getName());
    }
}
