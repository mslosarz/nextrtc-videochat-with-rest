package org.nextrtc.examples.videochat_with_rest.service;

import org.nextrtc.examples.videochat_with_rest.domain.User;
import org.nextrtc.examples.videochat_with_rest.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class VerifyUserService {

    @Autowired
    private UserRepository userRepository;

    public boolean verify(String key) {
        Optional<User> confirmationKey = userRepository.findByConfirmationKey(key);
        confirmationKey.ifPresent(User::confirmEmail);
        return confirmationKey.isPresent();
    }
}
