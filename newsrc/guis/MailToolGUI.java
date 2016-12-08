package guis;

import entities.Customer;
import org.jcsp.awt.ActiveButton;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.Parallel;
import processes.CarPark;
import processes.MailTool;
import services.VacancyService;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class MailToolGUI implements CSProcess {
    @Override
    public void run() {
        final Frame root = new Frame("Choose Inbox");

        final Parallel mailToolGUI;

        final String[] label = getLabels();

        if(label != null) {

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

            mailToolGUI = new Parallel(
                    new CSProcess[]{
                            new Parallel(button),
                            new InboxGUI(event)
                    });

            // adding the close function on the AWT event close
            root.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    root.dispose();
                }
            });

            new Thread(){
                public void run(){
                    mailToolGUI.run();
                }
            }.start();

        }
    }

    public String[] getLabels() {
        ArrayList<Customer> customerArrayList = VacancyService.getCustomerList();
        String[] buttonLabels = new String[customerArrayList.size()];
        int i = 0;

        for (Customer customer : customerArrayList) {
            buttonLabels[i] = "Customer " + customer.getId();
            i++;
        }

        if(buttonLabels.length != 0) {
            return buttonLabels;
        }

        return null;
    }
}
