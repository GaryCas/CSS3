package processes.email;

import entities.Customer;
import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;
import services.VacancyService;

import java.util.ArrayList;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class MailBag implements CSProcess{

    private AltingChannelInput in;

    public MailBag(AltingChannelInput in) {
        this.in = in;
    }

    @Override
    public void run() {
        while(true){
            String value = (String) in.read();
            if(value.equalsIgnoreCase("show")){
                // loop through all of the customers and list emails
                ArrayList<Customer> customers = new ArrayList<>();
                int i = 0;
                while(!VacancyService.isEmpty()) {
                    customers.add((Customer) VacancyService.customers.get());
                    System.out.println("customer " + customers.get(i).getId() + "'s emails: "
                            + customers.get(i).getEmails());
                }

                for (Customer customer : customers) {
                    VacancyService.customers.put(customer);
                }

            }
        }
    }
}
