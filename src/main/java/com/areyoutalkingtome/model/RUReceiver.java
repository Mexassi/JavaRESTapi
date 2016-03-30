package com.areyoutalkingtome.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Massimo on 29/03/2016.
 */

@Entity
public class RUReceiver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private Timestamp time;

    @JsonBackReference
    @ManyToOne
    private RUMessage message;

    protected RUReceiver() {

    }

    public RUReceiver(String name, RUMessage message) {
        this.name = name;
        this.message = message;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    public String getName() {
        return name;
    }

    public Timestamp getTime() {
        return time;
    }

    public RUMessage getMessage() {
        return message;
    }

    public void setMessage(RUMessage message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
