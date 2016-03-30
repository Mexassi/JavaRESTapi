package com.areyoutalkingtome.controller;

import com.areyoutalkingtome.AreYouTalkingToMeApplication;
import com.areyoutalkingtome.TestUtil;
import com.areyoutalkingtome.model.RUMessage;
import com.areyoutalkingtome.repo.RUMessageRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Massimo on 30/03/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AreYouTalkingToMeApplication.class)
@WebAppConfiguration
@Transactional
public class HealthControllerTest {

    @Autowired
    RUMessageRepository messageRepository;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc restMock;

    @Before
    public void setup() {
        this.restMock = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @Transactional
    public void testInboundPostRequest() {
        RUMessage message = new RUMessage("me", "blah blah");
        messageRepository.save(message);
        Iterable<RUMessage> messages = messageRepository.findAll();

        try {
            restMock.perform(get("/health")
                    .contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(messages)))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}