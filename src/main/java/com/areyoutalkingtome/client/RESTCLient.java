package com.areyoutalkingtome.client;

import com.areyoutalkingtome.model.RUMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RESTClient {

    private String sendToAddress;

    @Value("${send.to.address}")
    public void setSendToAddress(String sendToAddress) {
        this.sendToAddress = sendToAddress;
    }

    public void sendMessage(RUMessage message) {
        String uri = String.format("http://%s:2016/inbound", sendToAddress);
        System.out.println("Sending message");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<RUMessage> messageHttpEntity = new HttpEntity<RUMessage>(message);
        restTemplate.postForObject(uri, messageHttpEntity, RUMessage.class);
    }

    public String getHealth(String uri) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        return response.getBody();
    }
}
