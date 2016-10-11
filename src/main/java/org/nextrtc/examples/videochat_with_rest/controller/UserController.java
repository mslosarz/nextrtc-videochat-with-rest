package org.nextrtc.examples.videochat_with_rest.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @RequestMapping(value = {"/user", "/me"}, method = RequestMethod.GET)
    public String user(Principal principal) {
        return principal.getName();
    }

}
