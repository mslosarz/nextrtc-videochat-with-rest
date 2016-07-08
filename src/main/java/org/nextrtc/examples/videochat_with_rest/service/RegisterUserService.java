package org.nextrtc.examples.videochat_with_rest.service;

import org.nextrtc.examples.videochat_with_rest.domain.User;
import org.nextrtc.examples.videochat_with_rest.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sun.mail.util.MailConnectException;

import javax.transaction.Transactional;

import java.util.UUID;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
@Transactional
public class RegisterUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender javaMailSender;

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

        //TODO: call async
        try {
        	javaMailSender.send(prepareMail(user));
        } catch (Throwable mailIssue ) {
        	log.warn("Unable to send registration confirmation email", mailIssue);
        }

        userRepository.save(user);
    }

    //TODO: move to service, parameters from properties
    private SimpleMailMessage prepareMail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@nextrtc.org");
        message.setTo(user.getEmail());
        message.setSubject("Account verification");
        message.setText("enter site: /action/verify/" + user.getConfirmationKey());
        return message;
    }
}
