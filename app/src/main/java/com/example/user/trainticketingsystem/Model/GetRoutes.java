package com.example.user.trainticketingsystem.Model;

/**
 * Created by user on 5/13/2017.
 */

public class GetRoutes {
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    String from;
    String to;
}
