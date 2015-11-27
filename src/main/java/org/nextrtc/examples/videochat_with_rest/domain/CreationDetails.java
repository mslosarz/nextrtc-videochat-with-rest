/* Copyright 2015 Sabre Holdings */
package org.nextrtc.examples.videochat_with_rest.domain;

import lombok.Getter;
import org.joda.time.DateTime;

import javax.persistence.Entity;

@Entity
public class CreationDetails {

    @Getter
    private DateTime at = DateTime.now();

}
