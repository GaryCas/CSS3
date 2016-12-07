package processes;

import org.jcsp.awt.ActiveButton;
import org.jcsp.lang.*;
import processes.carpark.Arrivals;
import processes.carpark.Control;
import processes.carpark.Departs;
import processes.email.Dispatch;
import processes.email.MailBag;
import processes.email.UINT;
import services.VacancyService;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class MailTool implements CSProcess {



    @Override
    public void run() {
        One2OneChannel mailBag = Channel.one2one();
        One2OneChannel dispatch = Channel.one2one();

        final Frame root = new Frame("Mail Tool");

        // adding the close function on the AWT event close
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

        VacancyService.initCarpark(10);
        final String[] label = {"Mail Bag", "startup", "dispatch"};

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
                        new Dispatch(dispatch.in()),
                        new MailBag(mailBag.in()),
                        new UINT(event.in(), dispatch, mailBag)
                });


        MailTool.run();
    }
}
