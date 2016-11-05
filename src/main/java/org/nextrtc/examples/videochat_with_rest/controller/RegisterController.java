package org.nextrtc.examples.videochat_with_rest.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.nextrtc.examples.videochat_with_rest.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/action")
public class RegisterController {

    @Autowired
    private RegisterUserService service;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("username") String username, @ModelAttribute("password") String password, @ModelAttribute("email") String email, HttpServletRequest request) {
        String confirmationKey = service.generateConfirmationKey();
        service.register(username, password, email, confirmationKey);
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        return scheme + "://" + serverName + ":" + serverPort + "/action/verify/" + confirmationKey;
    }
}
