package processes.email;

import entities.Customer;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInput;
import services.TicketService;
import services.VacancyService;

import java.util.ArrayList;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class Dispatch implements CSProcess{

    private ChannelInput in;

    public Dispatch(ChannelInput in){
        this.in = in;
    }

    @Override
    public void run() {
        while(true){
            // this will actually get a hash value from TicketService and email it to a customer
            Customer customer = (Customer) in.read();
            String email = "";

            if(VacancyService.isFull() || TicketService.onlineTickets.getState() == 0){
                email = "Booking failed";
            } else {
                email = "ticket reference" + TicketService.onlineTickets.get();
            }

            ArrayList<String> emails = new ArrayList<>();
            emails.add(email);

            customer.setEmails(emails);
            System.out.println("Email dispatched to customer " + customer.getId());

        }
    }
}
