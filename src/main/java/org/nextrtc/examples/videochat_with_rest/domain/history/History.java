package org.nextrtc.examples.videochat_with_rest.domain.history;

import org.nextrtc.examples.videochat_with_rest.domain.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class History {

    private List<Call> calls;

    public History(Set<Member> conversationMember) {
        List<Member> sortedMembers = new ArrayList<>(conversationMember);
        sortedMembers.sort(Member::startedBefore);
        calls = sortedMembers.stream().map(Member::toCall).collect(toList());
    }

    public List<Call> getCalls() {
        return calls;
    }
}
