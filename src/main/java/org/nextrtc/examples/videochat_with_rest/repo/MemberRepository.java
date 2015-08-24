package org.nextrtc.examples.videochat_with_rest.repo;

import java.util.Optional;

import org.nextrtc.examples.videochat_with_rest.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByRtcId(@Param("rtcId") String rtcId);

}
