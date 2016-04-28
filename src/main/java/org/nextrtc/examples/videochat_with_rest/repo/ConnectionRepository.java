package org.nextrtc.examples.videochat_with_rest.repo;

import org.nextrtc.examples.videochat_with_rest.domain.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ConnectionRepository extends JpaRepository<Connection, Integer> {

}
