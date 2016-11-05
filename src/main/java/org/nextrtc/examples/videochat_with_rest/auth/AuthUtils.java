package org.nextrtc.examples.videochat_with_rest.auth;

import org.nextrtc.examples.videochat_with_rest.domain.User;
import org.nextrtc.examples.videochat_with_rest.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class AuthUtils {

    @Autowired
    private UserRepository userRepository;

    public User getAuthenticatedUser(Principal userPrincipal) {
        String name = userPrincipal.getName();
        Optional<User> byUsername = userRepository.findByUsername(name);
        if (!byUsername.isPresent()) {
            byUsername = userRepository.findByAuthProviderId(name);

        }
        return byUsername.orElseThrow(() -> new RuntimeException("User isn't authenticated!"));
    }
}
