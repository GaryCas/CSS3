package guis;

import org.jcsp.awt.ActiveButton;
import org.jcsp.lang.*;
import processes.OnlineBooking;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by rd019985 on 08/12/2016.
 */
public class BookingsGUI implements CSProcess{

    private One2AnyChannel bookingToEmailChannel;
    private Any2OneChannel carParkToBookingChannel;

    public BookingsGUI(One2AnyChannel bookingToEmailChannel, Any2OneChannel carParkToBookingChannel) {
        this.bookingToEmailChannel = bookingToEmailChannel;
        this.carParkToBookingChannel = carParkToBookingChannel;
    }

    @Override
    public void run() {


        final Parallel bookingsGUI = new Parallel(
                new CSProcess[]{
                        new OnlineBooking(bookingToEmailChannel, carParkToBookingChannel)
                });

        new Thread() {
            public void run() {
                bookingsGUI.run();
            }
        }.start();
    }
}
