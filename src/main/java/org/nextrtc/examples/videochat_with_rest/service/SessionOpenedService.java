package org.nextrtc.examples.videochat_with_rest.service;

import org.apache.log4j.Logger;
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
import java.util.Optional;

@Service
@Transactional
@PreAuthorize(value = "USER")
public class SessionOpenedService {

    private static final Logger log = Logger.getLogger(SessionOpenedService.class);
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
        String key = session.getUserPrincipal().getName();
        Optional<User> byUsername = userRepository.findByUsername(key);
        if (!byUsername.isPresent()) {
            return userRepository.findByAuthProviderId(key).orElse(null);

        }
        return byUsername.get();
    }
}
