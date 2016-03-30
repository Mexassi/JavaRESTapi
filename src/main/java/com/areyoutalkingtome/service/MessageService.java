package com.areyoutalkingtome.service;

import com.areyoutalkingtome.model.RUMessage;
import com.areyoutalkingtome.model.RUReceiver;
import com.areyoutalkingtome.repo.RUMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Created by Massimo on 30/03/2016.
 */

@Service
@EnableScheduling
public class MessageService {

    @Value("${send.to.address}")
    private String sendToAddress;
    @Value("${receiver.name}")
    private String receiverName;
    @Value("${origin.name}")
    private String origin;
    @Value("${message.content}")
    private String body;
    private static Logger log = Logger.getLogger(MessageService.class.getName());

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

    private void doSend() {
        RUMessage message = new RUMessage(origin, body);
    }


    @Scheduled(fixedDelay=6000)
    private void scheduleRun() {
        System.out.println("I've just run !");
        doSend();
    }
}
