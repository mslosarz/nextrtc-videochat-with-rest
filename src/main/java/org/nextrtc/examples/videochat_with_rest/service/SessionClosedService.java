package org.nextrtc.examples.videochat_with_rest.service;

import org.nextrtc.examples.videochat_with_rest.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SessionClosedService {

    @Autowired
    private MemberRepository memberRepository;

    public void execute(String memberId, Optional<String> reasonOfClose) {
        memberRepository.getByRtcId(memberId).disconnectWithReason(reasonOfClose);
    }
}
