package org.nextrtc.examples.videochat_with_rest.service;

import lombok.extern.log4j.Log4j;
import org.nextrtc.examples.videochat_with_rest.domain.User;
import org.nextrtc.examples.videochat_with_rest.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Log4j
@Service
@Transactional
public class RegisterUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public String generateConfirmationKey() {
    	return UUID.randomUUID().toString();
    }
    
    public void register(String username, String password, String email, String confirmationKey) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("user exists!");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("email is occupied!");
        }

        User user = new User(username, encoder.encode(password), email, confirmationKey);

        //TODO: send email

        userRepository.save(user);
    }
}
