package processes;

import org.jcsp.awt.ActiveButton;
import org.jcsp.lang.*;
import processes.bookings.Booking;
import processes.bookings.BookingGUI;
import services.VacancyService;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class OnlineBooking {

    public static void main (String[] args) {
        One2OneChannel booking = Channel.one2one();

        final Frame root = new Frame("Online Booking System");

        // adding the close function on the AWT event close
        root.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                root.dispose();
            }
        });

        VacancyService.initCarpark(10);
        final String[] label = {"book", "cancel booking"};

        final Any2OneChannel event = Channel.any2one();

        final ActiveButton[] button = new ActiveButton[label.length];

        for (int i = 0; i < label.length; i++) {
            button[i] = new ActiveButton(null, event.out(), label[i]);
        }

        root.setSize(300, 200);
        root.setLayout(new GridLayout(label.length / 2, 2));

        for (int i = 0; i < label.length; i++) {
            root.add(button[i]);
        }

        root.setVisible(true);


        Parallel MailTool = new Parallel(
                new CSProcess[]{
                        new Parallel(button),
                        new Booking(booking.in()),
                        new BookingGUI(event.in(), booking)
                });

        MailTool.run();

    }
}