package processes;

import org.jcsp.lang.*;
import processes.bookings.Booking;
import processes.bookings.BookingComm;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class OnlineBooking implements CSProcess {

    private Any2OneChannel event;

    public OnlineBooking(Any2OneChannel event) {

        this.event = event;
    }

    public void run () {
        One2OneChannel booking = Channel.one2one();

        final Parallel OnlineBooking = new Parallel(
                new CSProcess[]{
                        new Booking(booking.in()),
                        new BookingComm(event.in(), booking)
                });

        new Thread(){
            public void run(){
                OnlineBooking.run();
            }
        }.start();

    }
}
