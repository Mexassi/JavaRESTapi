package com.areyoutalkingtome.controller;

import com.areyoutalkingtome.model.RUMessage;
import com.areyoutalkingtome.service.MessageService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Massimo on 29/03/2016.
 */
@RestController
public class RUController {

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/inbound", method = RequestMethod.POST)
    public ResponseEntity<RUMessage> createMessage(@RequestBody RUMessage message) {
        return new ResponseEntity<RUMessage>(messageService.save(message), HttpStatus.CREATED);
    }


}
