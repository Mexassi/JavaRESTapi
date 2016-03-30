package com.areyoutalkingtome.service;

import com.areyoutalkingtome.AreYouTalkingToMeApplication;
import com.areyoutalkingtome.model.RUMessage;
import com.areyoutalkingtome.repo.RUMessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.*;

/**
 * Created by Massimo on 30/03/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AreYouTalkingToMeApplication.class)
@WebAppConfiguration
@Transactional
public class MessageServiceTest {

    @Autowired
    RUMessageRepository messageRepository;

    @Autowired
    MessageService messageService;

    public RUMessage message;

    @Before
    public void setup() {
        message = new RUMessage("me", "message sample");
    }

    @Test
    public void saveMessage() {
        RUMessage saved = messageService.save(message);
        assertTrue("should return message with incremented id", saved.getId() > 0);
        assertThat("should return message with id", saved.getId(), greaterThan(Long.parseLong("0")));
    }

    @Test
    public void addReceiver() {
        RUMessage newMessage = new RUMessage("me", "another message");
        assertThat("should not have the receiver yet", newMessage.getReceivers().size(), equalTo(0));
        messageService.save(newMessage);
        assertThat("receiver should be set", newMessage.getReceivers().size(), equalTo(1));
    }

    @Test
    public void sendMessages() {

    }

}