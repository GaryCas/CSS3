package processes.carpark;

import entities.Customer;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInput;
import org.jcsp.lang.One2OneChannel;
import services.VacancyService;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class Arrivals implements CSProcess{
    private ChannelInput arrive;
    private Any2OneChannel carParkToBookingChannel;


    public Arrivals(ChannelInput in, Any2OneChannel carParkToBookingChannel){
        this.arrive = in;
        this.carParkToBookingChannel = carParkToBookingChannel;
    }

    public void run(){

        while(true){
            String value = (String) arrive.read();
            carParkToBookingChannel.out().write(value);

        }
    }
}
