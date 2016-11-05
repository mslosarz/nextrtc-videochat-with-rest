package org.nextrtc.examples.videochat_with_rest.controller;

import org.nextrtc.examples.videochat_with_rest.domain.history.History;
import org.nextrtc.examples.videochat_with_rest.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/action/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public History getHistory(Principal authentication) {
        return historyService.getHistoryFor(authentication);
    }


}
