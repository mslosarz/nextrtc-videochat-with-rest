package org.nextrtc.examples.videochat_with_rest;

import org.nextrtc.signalingserver.api.NextRTCEndpoint;
import org.nextrtc.signalingserver.codec.MessageDecoder;
import org.nextrtc.signalingserver.codec.MessageEncoder;

import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/signaling",//
        decoders = MessageDecoder.class,//
        encoders = MessageEncoder.class)
public class MyEndpoint extends NextRTCEndpoint {
}
