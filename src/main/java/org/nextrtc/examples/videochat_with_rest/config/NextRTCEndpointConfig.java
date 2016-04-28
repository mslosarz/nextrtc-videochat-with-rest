package org.nextrtc.examples.videochat_with_rest.config;

import org.nextrtc.signalingserver.NextRTCConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(NextRTCConfig.class)
public class NextRTCEndpointConfig {

}