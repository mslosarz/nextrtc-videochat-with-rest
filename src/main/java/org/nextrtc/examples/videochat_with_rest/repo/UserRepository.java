package org.nextrtc.examples.videochat_with_rest.repo;

import org.nextrtc.examples.videochat_with_rest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@org.springframework.stereotype.Repository
@RepositoryRestResource(exported = false)
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(@Param("username") String username);

    Optional<User> findByEmail(@Param("email") String email);

    Optional<User> findByConfirmationKey(@Param("confirmationKey") String key);

    Optional<User> findByAuthProviderId(@Param("authProviderId") String key);


}
