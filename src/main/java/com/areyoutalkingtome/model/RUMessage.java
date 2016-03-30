package com.areyoutalkingtome.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    private String body;
    private Timestamp timeSent;

    @JsonManagedReference
    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RUReceiver> receivers;

    public RUMessage() {
//        this.receivers = new ArrayList<RUReceiver>();
    }

    public RUMessage(String origin, String body) {
        this.origin = origin;
        this.body = body;
        this.timeSent = new Timestamp(System.currentTimeMillis());
        this.receivers = new ArrayList<RUReceiver>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<RUReceiver> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<RUReceiver> receivers) {
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

    @Override
    public String toString() {
        return "RUMessage{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", body='" + body + '\'' +
                ", timeSent=" + timeSent +
                '}';
    }

    public void addReceiver(RUReceiver receiver) {
        receivers.add(receiver);
    }
}
