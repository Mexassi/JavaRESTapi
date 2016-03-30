package com.areyoutalkingtome.service;

import com.areyoutalkingtome.model.RUMessage;
import com.areyoutalkingtome.model.RUReceiver;
import com.areyoutalkingtome.repo.RUMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Created by Massimo on 30/03/2016.
 */

@Service
public class MessageService {

    private static Logger log = Logger.getLogger(MessageService.class.getName());

    @Autowired
    RUMessageRepository messageRepository;

    public RUMessage save(RUMessage message) {
        log.info("Saving message: " + message.toString());
        addReceiver(message);
        return messageRepository.save(message);
    }

    private void addReceiver(RUMessage message) {
        message.add(new RUReceiver("Massimo", message));
    }
}
