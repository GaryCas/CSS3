package guis;

import entities.Customer;
import org.jcsp.awt.ActiveButton;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2AnyChannel;
import org.jcsp.lang.SharedChannelInput;
import services.VacancyService;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Created by rd019985 on 08/12/2016.
 */
public class InboxGUI implements CSProcess {
    private Any2OneChannel event;
    private SharedChannelInput bookingToEmailChannel;

    public InboxGUI(Any2OneChannel event, SharedChannelInput bookingToEmailChannel) {
        this.event = event;
        this.bookingToEmailChannel = bookingToEmailChannel;
    }

    @Override
    public void run() {
        while(true){
            String eventValue = String.valueOf(event.in().read()).toLowerCase();
            String[] values = eventValue.split(" ");

            switch(values[0]){
                case "customer":
                    Customer currentCustomer = getCurrentCustomer(values);
                    launchGUI(currentCustomer);
                    break;
                default:
                    System.out.println("Not sure about that one");
                    break;
            }

            String bookingChannelInput = String.valueOf(bookingToEmailChannel.read());
            System.out.println("something");

            switch(bookingChannelInput){
                default:
                    System.out.println("Inbox defaulting");
                    break;
            }
        }
    }

    private void launchGUI(Customer currentCustomer) {
        final Frame root = new Frame(currentCustomer.getId() + " Email Service");

        // adding the close function on the AWT event close
        root.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                root.dispose();
            }
        });

        final String[] label = {"next", "prev", "delete"};

        final ActiveButton[] button = new ActiveButton[label.length];

        for (int i = 0; i < label.length; i++) {
            button[i] = new ActiveButton(null, this.event.out(), label[i]);
        }

        root.setSize(300, 200);
        root.setLayout(new GridLayout(label.length / 2, 2));

        for (int i = 0; i < label.length; i++) {
            root.add(button[i]);
        }

        root.setVisible(true);

        int count = 0;

        while(true) {
            ArrayList<String> emails = currentCustomer.getEmails();

            String value = String.valueOf(event.in().read());
            switch (value) {
                case "next":
                    if(emails.size() < count + 1)
                        count++;
                    break;
                case "prev":
                    if(count >= 1)
                        count--;
                    break;
                case "delete":
                    emails.remove(count);
                    currentCustomer.setEmails(emails);
                    break;
                default:
                    System.out.println("value " + value);
                    break;
                }

                if(emails.size() != 0)
                    System.out.println(emails.get(count));

        }
    }

    /**
     * Gets the current customer from the Customer buffer in the vacancy service
     * @param values
     * @return current customer
     */
    public Customer getCurrentCustomer(String[] values) {
        for (Customer customer : VacancyService.getCustomerList()) {
            if(values[1].equalsIgnoreCase(String.valueOf(customer.getId()))){
                return customer;
            }
        };
        return null;
    }
}
