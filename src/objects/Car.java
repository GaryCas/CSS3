package objects;

import java.util.Random;

/**
 * Created by rd019985 on 28/11/2016.
 */
public class Car {
    private int ticketHashcode = -1;
    private int id;
    private boolean hasTicket;

    // for online bookings
    public Car(boolean hasTicket) {

        this.id = new Random().nextInt();
        if(hasTicket) {
            this.ticketHashcode = hashCode();
        }
    }

    /**
     * to be used when a arrives.
     * @return
     */
    public boolean hasTicket(){
        return hasTicket;
    }

    public int getTicketHashcode() {
        return ticketHashcode;
    }

}
