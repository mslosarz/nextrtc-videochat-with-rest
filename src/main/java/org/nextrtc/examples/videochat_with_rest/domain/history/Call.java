package org.nextrtc.examples.videochat_with_rest.domain.history;

import java.util.Date;
import java.util.List;

public class Call {
    private List<String> others;
    private Date started;
    private int duration;

    public Call(List<String> others) {
    }
}
