package org.nextrtc.examples.videochat_with_rest.controller;

import org.nextrtc.examples.videochat_with_rest.auth.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/action/check")
public class CheckMyName {

    @Autowired
    private AuthUtils authUtils;

    @RequestMapping(value = "name", method = RequestMethod.GET)
    public String check(Principal authentication) {
        return authUtils.getAuthenticatedUser(authentication).getUsername();
    }
}
