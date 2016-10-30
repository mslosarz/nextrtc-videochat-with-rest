package org.nextrtc.examples.videochat_with_rest.service;

import org.nextrtc.examples.videochat_with_rest.auth.AuthUtils;
import org.nextrtc.examples.videochat_with_rest.domain.User;
import org.nextrtc.examples.videochat_with_rest.domain.history.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
@Transactional
public class HistoryService {

    @Autowired
    private AuthUtils authUtils;

    public History getHistoryFor(Principal principal) {
        User user = authUtils.getAuthenticatedUser(principal);

        return user.prepareHistory();
    }
}
