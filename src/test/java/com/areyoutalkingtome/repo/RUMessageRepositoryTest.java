package com.areyoutalkingtome.repo;

import com.areyoutalkingtome.AreYouTalkingToMeApplication;
import com.areyoutalkingtome.model.RUMessage;
import com.areyoutalkingtome.model.RUReceiver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AreYouTalkingToMeApplication.class)
@WebAppConfiguration
@Transactional
public class RUMessageRepositoryTest {

    @Autowired
    RUMessageRepository messageRepository;

    @Test
    public void testSaveWithReceiver() throws Exception {
        RUMessage message = new RUMessage("Massimo", "good luck testing it!");
        message.addReceiver(new RUReceiver("somebody", message));
        RUMessage saved = messageRepository.save(message);
        RUMessage inserted = messageRepository.findOne(saved.getId());
        assertThat("should have the same origin", saved.getOrigin(), equalTo(inserted.getOrigin()));
        RUReceiver receiver = inserted.getReceivers().get(0);
        assertThat("receiver should have an id", receiver.getId(), equalTo(saved.getReceivers().get(0).getId()));
    }

    @Before
    public void setUp() throws Exception {


    }
}