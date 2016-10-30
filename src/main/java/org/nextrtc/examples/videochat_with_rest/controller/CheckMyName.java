package org.nextrtc.examples.videochat_with_rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/action/check")
public class CheckMyName {

    @RequestMapping(value = "name", method = RequestMethod.GET)
    public String verify(Principal authentication) {
        return authentication.getName();
    }
}
