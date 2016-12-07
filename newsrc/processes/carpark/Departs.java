package processes.carpark;

import entities.Customer;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInput;
import services.VacancyService;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class Departs implements CSProcess {
    private ChannelInput depart;

    public Departs(ChannelInput in){
        this.depart = in;
    }

    public void run(){
        while(true){
            if(String.valueOf(depart.read()).equalsIgnoreCase("depart")) {
                Customer value = (Customer) VacancyService.customers.get();
                System.out.println("customer " + value.getId() + " has left");
            }
        }
    }
}
