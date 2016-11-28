import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.jcsp.lang.*;
import org.jcsp.util.*;
import org.jcsp.awt.*;
import processes.Arrives;
import processes.Control;
import processes.Departs;


class Main {

    public static void main (String argv[]) {

        Any2OneChannel depart = Channel.any2one();
        Any2OneChannel arrive =  Channel.any2one();

        final Frame root = new Frame ("ActiveButton Example");

        // adding the close function on the AWT event close
        root.addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent we) {
                                  root.dispose();
                              }
                          });

        final String[] label = {"arrive", "depart"};

        final Any2OneChannel event = Channel.any2one (new OverWriteOldestBuffer (10));

        final ActiveButton[] button = new ActiveButton[label.length];
        for (int i = 0; i < label.length; i++) {
            button[i] = new ActiveButton (null, event.out (), label[i]);
        }

        root.setSize (300, 200);
        root.setLayout (new GridLayout (label.length/2, 2));
        for (int i = 0; i < label.length; i++) {
            root.add (button[i]);
        }
        root.setVisible (true);

        new Parallel (
                new CSProcess[]{
                        new Parallel(button),
                        new Control(event.in(), arrive, depart),
                        new Arrives(arrive.in()),
                        new Departs(depart.in()),

                }
        ).run ();
    }

}