package org.nextrtc.examples.videochat_with_rest.repo;

import org.nextrtc.examples.videochat_with_rest.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByRtcId(@Param("rtcId") String rtcId);

    Member getByRtcId(@Param("rtcId") String rtcId);

}
