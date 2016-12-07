package processes;

import org.jcsp.awt.ActiveButton;
import org.jcsp.lang.*;
import processes.carpark.Arrivals;
import processes.carpark.Control;
import processes.carpark.Departs;
import services.VacancyService;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class CarPark  {

    private AltingChannelInput in;

    public CarPark(AltingChannelInput in){
        this.in = in;
    }

    public static void main (String[] args) {
        One2OneChannel arrive = Channel.one2one();
        One2OneChannel depart = Channel.one2one();
        Parallel CarPark;

        final Frame root = new Frame("Car Park");

        // adding the close function on the AWT event close
        root.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                root.dispose();
            }
        });

        VacancyService.initCarpark(10);
        final String[] label = {"arrive", "book", "depart"};

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


        CarPark = new Parallel(
                new CSProcess[]{
                        new Parallel(button),
                        new Arrivals(arrive.in()),
                        new Control(arrive, depart, event.in()),
                        new Departs(depart.in())});

        CarPark.run();

    }
}
