package processes;

import org.jcsp.lang.*;
import processes.bookings.Booking;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class OnlineBooking implements CSProcess {

    private One2AnyChannel bookingToEmailChannel;
    private Any2OneChannel carParkToBookingChannel;

    public OnlineBooking(One2AnyChannel bookingToEmailChannel, Any2OneChannel carParkToBookingChannel) {
        this.bookingToEmailChannel = bookingToEmailChannel;
        this.carParkToBookingChannel = carParkToBookingChannel;
    }

    public void run () {

        final Parallel OnlineBooking = new Parallel(
                new CSProcess[]{
                        new Booking(bookingToEmailChannel.out(), carParkToBookingChannel.in())
                });

        new Thread(){
            public void run(){
                OnlineBooking.run();
            }
        }.start();

    }
}
