package com.areyoutalkingtome.service;

import com.areyoutalkingtome.client.RESTClient;
import com.areyoutalkingtome.model.RUMessage;
import com.areyoutalkingtome.model.RUReceiver;
import com.areyoutalkingtome.repo.RUMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Massimo on 30/03/2016.
 */

@Service
@EnableScheduling
public class MessageService {

    private static Logger log = Logger.getLogger(MessageService.class.getName());

    @Autowired
    RESTClient restClient;

    @Value("${receiver.name}")
    private String receiverName;

    @Autowired
    RUMessageRepository messageRepository;

    public RUMessage save(RUMessage message) {
        log.info("Saving message: " + message.toString());
        addReceiver(message);
        return messageRepository.save(message);
    }

    private void addReceiver(RUMessage message) {
        message.add(new RUReceiver(receiverName, message));
    }

    @Scheduled(fixedDelay=6000)
    public void scheduleRun() {
        // get all messages
        List<RUMessage> messages = (List<RUMessage>) messageRepository.findAll();
        // for each
        for (RUMessage message : messages) {
            restClient.sendMessage(message);
        }

            // send with restclient
            // if success
                // delete
    }

}
