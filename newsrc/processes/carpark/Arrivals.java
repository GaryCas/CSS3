package processes.carpark;

import entities.Customer;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInput;
import services.VacancyService;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class Arrivals implements CSProcess{
    private ChannelInput arrive;


    public Arrivals(ChannelInput in){
        this.arrive = in;
    }

    public void run(){

        while(true){
            Customer value = (Customer) arrive.read();
            VacancyService.customers.put(value);
            System.out.println("customer " + value.getId() + " has arrived");
        }
    }
}
