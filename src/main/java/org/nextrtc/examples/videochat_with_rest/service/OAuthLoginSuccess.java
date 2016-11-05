package org.nextrtc.examples.videochat_with_rest.service;

import org.apache.log4j.Logger;
import org.nextrtc.examples.videochat_with_rest.domain.handler.ConversationCreatedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Transactional
public class OAuthLoginSuccess implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
    private static final Logger log = Logger.getLogger(ConversationCreatedHandler.class);

    @Autowired
    private RegisterUserService registerUserService;

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent authenticationSuccessEvent) {
        Authentication authentication = authenticationSuccessEvent.getAuthentication();
        if (!(authentication instanceof OAuth2Authentication)) {
            return;
        }
        OAuth2Authentication auth = (OAuth2Authentication) authentication;
        Map<String, String> details = (Map) auth.getUserAuthentication().getDetails();
        String complex = auth.getPrincipal().toString();
        registerUserService.register(details.get("name"), details.get("email"), complex);
    }
}