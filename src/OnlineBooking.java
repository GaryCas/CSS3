import objects.Car;
import org.jcsp.util.Buffer;
import processes.Control;

/**
 * Created by rd019985 on 28/11/2016.
 */
public class OnlineBooking {

    public void makeReservation(){
        if (!Control.isFull()){
            Car car = new Car(true);
            Control.spaces.put(car);
            Control.onlineTickets.put(car.getTicketHashcode());
        };
    }
}
