package entities;

import services.TicketService;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class Customer {

    private int id;
    private int ticketHash;
    private ArrayList<String> emails;

    public Customer(int id, int ticketHash){
        this.id = id;
        this.ticketHash = ticketHash;
    }

    public Customer() {
        this.id = Math.abs(new Random().nextInt());
    }

    public void addTicket(int ticketHash){
        TicketService.onlineTickets.put(new Object().hashCode());
    }

    public void sendEmail(int ticketHash){
        this.ticketHash = ticketHash;
    }

    public int getId() {
        return id;
    }

    public int getTicketHash() {
        return ticketHash;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }
}
