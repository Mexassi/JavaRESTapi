package com.areyoutalkingtome.controller;

import com.areyoutalkingtome.model.RUMessage;
import com.areyoutalkingtome.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Massimo on 29/03/2016.
 */
@RestController
@EnableScheduling
public class RUController {

    @Autowired
    MessageService messageService;

    @Value("${send.to.address}")
    private String sendToAddress;

    @RequestMapping(value = "/inbound", method = RequestMethod.POST)
    public ResponseEntity<RUMessage> createMessage(@RequestBody RUMessage message) {
        return new ResponseEntity<RUMessage>(messageService.save(message), HttpStatus.CREATED);
    }

    private void doSend() {

    }


    @Scheduled(fixedDelay=6000)
    private void scheduleRun() {
        System.out.println("I've just run !");
    }

}
