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

    private One2AnyChannel channel;

    public BookingsGUI(One2AnyChannel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        One2OneChannel booking = Channel.one2one();

        final Frame root = new Frame("Online Booking System");

        // adding the close function on the AWT event close
        root.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                root.dispose();
            }
        });
        final String[] label = {"book", "list bookings"};

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

        final Parallel bookingsGUI = new Parallel(
                new CSProcess[]{
                        new Parallel(button),
                        new OnlineBooking(event, channel)
                });

        new Thread() {
            public void run() {
                bookingsGUI.run();
            }
        }.start();
    }
}
