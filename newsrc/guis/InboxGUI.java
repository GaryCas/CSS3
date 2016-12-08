package guis;

import entities.Customer;
import org.jcsp.awt.ActiveButton;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.CSProcess;
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

    public InboxGUI(Any2OneChannel event) {
        this.event = event;
    }

    @Override
    public void run() {
        while(true){
            String value = String.valueOf(event.in().read()).toLowerCase();
            String[] values = value.split(" ");

            switch(values[0]){
                case "customer":
                    Customer currentCustomer = getCurrentCustomer(values);
                    launchGUI(currentCustomer);
                    break;
                default:
                    System.out.println("Not sure about that one");
                    break;
            }
        }
    }

    private void launchGUI(Customer currentCustomer) {
        System.out.println("loading inbox for " + currentCustomer.getId());

        final Frame root = new Frame(currentCustomer.getId() + " Email Service");

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
            if(emails.size() == 0){
                System.out.println(currentCustomer.getId() + " has no emails");
                root.dispose();
                return;
            } else{
                System.out.println(emails.get(count));
                String value = String.valueOf(event.in().read());
                switch (value) {
                    case "next":
                        count++;
                        break;
                    case "prev":
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
            }
        }
    }

    public Customer getCurrentCustomer(String[] values) {
        for (Customer customer : VacancyService.getCustomerList()) {
            if(values[1].equalsIgnoreCase(String.valueOf(customer.getId()))){
                return customer;
            }
        };
        return null;
    }
}
