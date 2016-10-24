package org.nextrtc.examples.videochat_with_rest.controller;

import org.nextrtc.examples.videochat_with_rest.service.VerifyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/action/verify")
public class VerificationController {

    @Autowired
    private VerifyUserService service;

    @RequestMapping(value = "{key}", method = RequestMethod.GET)
    @ResponseBody
    public String verify(@PathVariable String key, Model model) {
        return "" + service.verify(key);
    }
}
