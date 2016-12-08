package processes.carpark;

import entities.Customer;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInput;
import services.VacancyService;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class Departs implements CSProcess {
    private ChannelInput depart;
    private Any2OneChannel carParkToBookingChannel;

    public Departs(ChannelInput in, Any2OneChannel carParkToBookingChannel){
        this.depart = in;
        this.carParkToBookingChannel = carParkToBookingChannel;
    }

    public void run(){
        while(true){
            if(String.valueOf(depart.read()).equalsIgnoreCase("depart")) {
                Customer value = (Customer) VacancyService.customers.get();
                carParkToBookingChannel.out().write("depart " + value.getId() + " " + value.getTicketHash());
            }
        }
    }
}
