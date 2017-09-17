package com.example.user.trainticketingsystem.Model;

/**
 * Created by user on 5/13/2017.
 */

public class GetTickets {
    String id;
    String from;

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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    String destination;
    String amount;

}
