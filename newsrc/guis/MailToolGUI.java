package guis;

import entities.Customer;
import org.jcsp.awt.ActiveButton;
import org.jcsp.lang.*;
import services.VacancyService;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class MailToolGUI implements CSProcess {
    private One2AnyChannel bookingToEmailChannel;
    private Frame root;

    public MailToolGUI(One2AnyChannel bookingToEmailChannel) {
        this.bookingToEmailChannel = bookingToEmailChannel;
        root = new Frame("Choose Inbox");
    }

    @Override
    public void run() {

        initRoot();
        doButtons();
        startUpMailToolGUI();

        while(true) {
            String inputValue = String.valueOf(bookingToEmailChannel.in().read());

            String[] inputComponents = inputValue.split(":");

            switch (inputComponents[0]) {
                case "BC":
                    Customer customer = new Customer(inputComponents[1]);
                    VacancyService.customers.put(customer);
                    doButtons();
                    break;
                default:
                    System.out.println("defaulting Mail Tool gui");
                    break;
            }
        }
    }

    private void startUpMailToolGUI() {
    }

    private void doButtons() {
        final Parallel mailToolGUI;
        final Any2OneChannel event = Channel.any2one();
        ActiveButton [] buttons = new ActiveButton[10];

        String[] label = getLabels();

        if(label != null) {
            root.setLayout(new GridLayout(label.length / 2, 2));

            buttons[label.length - 1] = new ActiveButton(null, event.out(), label[label.length - 1]);

            root.add(buttons[label.length - 1]);

            mailToolGUI = new Parallel(
                    new CSProcess[]{
                            new Parallel(buttons),
                            new InboxGUI(event, bookingToEmailChannel.in())
                    });


            new Thread() {
                public void run() {
                    mailToolGUI.run();
                }
            }.start();

            root.setVisible(true);
        }
    }

    private void initRoot() {
        root.setSize(300, 200);


        root.setVisible(true);

        // adding the close function on the AWT event close
        root.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                root.dispose();
            }
        });

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
