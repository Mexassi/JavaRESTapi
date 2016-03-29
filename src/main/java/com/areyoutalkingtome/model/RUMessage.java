package com.areyoutalkingtome.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Massimo on 29/03/2016.
 */

@Entity
public class RUMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String origin;
    @OneToMany(targetEntity = RUReceiver.class)
    private List receivers;
    private String body;
    private Timestamp timeSent;


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List getReceivers() {
        return receivers;
    }

    public void setReceivers(List receivers) {
        this.receivers = receivers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Timestamp timeSent) {
        this.timeSent = timeSent;
    }
}
