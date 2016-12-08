package guis;

import org.jcsp.awt.ActiveButton;
import org.jcsp.lang.*;
import processes.CarPark;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class CarParkGUI implements CSProcess{

    private Any2OneChannel carParkToBookingChannel;

    public CarParkGUI(Any2OneChannel carParkToBookingChannel) {
        this.carParkToBookingChannel = carParkToBookingChannel;
    }

    public  void run(){
        final Frame root = new Frame("Car Park");

        final Parallel carParkGUI;

        final String[] label = {"arrive", "list bookings", "depart"};

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

        carParkGUI = new Parallel(
                new CSProcess[]{
                        new Parallel(button),
                        new CarPark(event.in(), carParkToBookingChannel)
                });

        // adding the close function on the AWT event close
        root.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                root.dispose();
            }
        });

        carParkGUI.run();
    }
}
