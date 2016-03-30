package com.areyoutalkingtome.controller;

import com.areyoutalkingtome.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Massimo on 30/03/2016.
 */
@RestController
public class HealthController {

    private enum Endpoint {
        TOM("111", "Tom"), STEF("117", "Stef"), ROB("105", "Rob"), MASS("106", "Mass");

        String prefix = "http://192.168.1.";
        String suffix = ":2016/inbound";
        private final String uri;
        private final String name;
        Endpoint(String ip, String name) {
            this.uri = prefix + ip + suffix;
            this.name = name;
        }
        public String getUri() {
            return uri;
        }

        public String getName() {
            return name;
        }
    }

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public Map<String, String> getHealth() {
        Map<String, String> result = new HashMap<String, String>();
        for (Endpoint endpoint : Endpoint.values()) {
            String response;
            try {
                response = messageService.getHealth(endpoint.getUri());
            } catch (Exception e) {
                response = e.getMessage();
            }
            result.put(endpoint.getName(), response);
        }

        return result;
    }

}
