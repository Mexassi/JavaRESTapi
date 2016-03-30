package com.areyoutalkingtome.client;

import com.areyoutalkingtome.model.RUMessage;
import com.areyoutalkingtome.service.MessageService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

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