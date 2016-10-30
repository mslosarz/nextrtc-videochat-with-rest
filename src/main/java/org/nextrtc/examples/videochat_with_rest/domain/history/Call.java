package org.nextrtc.examples.videochat_with_rest.domain.history;

import java.util.Date;
import java.util.List;

public class Call {
    private List<String> otherRtcIds;
    private Date started;
    private Long duration;
    private boolean inProgress;
    private List<String> otherNames;

    public Call(List<String> members, boolean closed, Date begin, Long duration) {
        this.otherRtcIds = members;
        this.started = begin;
        this.duration = duration;
        this.inProgress = !closed;
    }

    public List<String> getOtherRtcIds() {
        return otherRtcIds;
    }

    public Date getStarted() {
        return started;
    }

    public Long getDuration() {
        return duration;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setOtherNames(List<String> otherNames) {
        this.otherNames = otherNames;
    }
}
