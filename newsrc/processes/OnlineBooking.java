package processes;

import org.jcsp.lang.*;
import processes.bookings.Booking;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class OnlineBooking implements CSProcess {

    private Any2OneChannel eventChannel;
    private One2AnyChannel interProcessChannel;

    public OnlineBooking(Any2OneChannel eventChannel, One2AnyChannel interProcessChannel) {
        this.eventChannel = eventChannel;
        this.interProcessChannel = interProcessChannel;
    }

    public void run () {
        One2OneChannel booking = Channel.one2one();

        final Parallel OnlineBooking = new Parallel(
                new CSProcess[]{
                        new Booking(eventChannel.in(), interProcessChannel.out())
                });

        new Thread(){
            public void run(){
                OnlineBooking.run();
            }
        }.start();

    }
}
