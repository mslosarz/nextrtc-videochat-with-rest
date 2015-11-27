/* Copyright 2015 Sabre Holdings */
package org.nextrtc.examples.videochat_with_rest.domain;

import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import java.util.Optional;

@Entity
@ToString
public class DestroyDetails {

    DestroyDetails() {

    }

    DestroyDetails(Optional<String> cause) {
        cause.ifPresent(causeMessage -> this.cause = causeMessage);
    }

    @Getter
    private DateTime at = DateTime.now();

    @Getter
    private String cause;
}
