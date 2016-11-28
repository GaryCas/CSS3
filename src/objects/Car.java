package objects;

import java.util.Random;

/**
 * Created by rd019985 on 28/11/2016.
 */
public class Car {
    private int id;
    private int ticketHashcode = -1;
    private String carName;

    // for online bookings
    public Car() {
        this.ticketHashcode = hashCode();
        this.id = this.ticketHashcode;
    }

    public Car(String carName) {
        this.carName = carName;
        this.id = new Random().nextInt();
    }

    public boolean hasTicket(){
        if (ticketHashcode == -1){
            return false;
        }
        return true;
    }

}
