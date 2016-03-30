package com.areyoutalkingtome.service;

import com.areyoutalkingtome.client.RESTClient;
import com.areyoutalkingtome.model.RUMessage;
import com.areyoutalkingtome.model.RUReceiver;
import com.areyoutalkingtome.repo.RUMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    RUMessageRepository messageRepository;

    @Value("${receiver.name}")
    private String receiverName;
    @Value("${rob.address}")
    private String robAddress;
    @Value("${stef.address}")
    private String stefAddress;
    @Value("${tom.address}")
    private String tomAddress;
    @Value("${mass.address}")
    private String massAddress;

    private ArrayList<String> addresses = new ArrayList<String>();

    public RUMessage save(RUMessage message) {
        log.info("Saving message: " + message.toString());
        System.out.println("Saving message: " + message.toString());
        addReceiver(message);
        return messageRepository.save(message);
    }

    private void addReceiver(RUMessage message) {
        message.addReceiver(new RUReceiver(receiverName, message));
    }

    @Scheduled(fixedDelay=30000)
    public void scheduleRun() {
        List<RUMessage> messages = (List<RUMessage>) messageRepository.findAll();
        for (RUMessage message : messages) {
            try {
                restClient.sendMessage(message);
                messageRepository.delete(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Iterable<RUMessage> getAllMessages() {
        return messageRepository.findAll();
    }

    public String getHealth(String address) {
        return restClient.getHealth(address);
    }
}
