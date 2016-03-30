package com.areyoutalkingtome.client;

import com.areyoutalkingtome.model.RUMessage;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class RESTClientTest {

    RESTClient messageClient;

    @Before
    public void setUp() throws Exception {
        messageClient = new RESTClient();
        messageClient.setSendToAddress("localhost");
    }

    @Ignore
    @Test
    public void sendMessage() {

        messageClient.sendMessage(new RUMessage("me", "blah blha"));

    }

}