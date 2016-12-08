package processes.carpark;

import entities.Customer;
import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannel;
import org.jcsp.lang.One2OneChannelInt;
import services.VacancyService;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class Control implements CSProcess{

    private final One2OneChannel arrive;
    private final One2OneChannel depart;
    private AltingChannelInput in;
    private int i;

    public Control(One2OneChannel arrive, One2OneChannel depart, AltingChannelInput in) {
        this.arrive = arrive;
        this.depart = depart;
        this.in = in;

        VacancyService.initCarpark(10);
    }

    @Override
    public void run() {

        while (true) {

            switch (String.valueOf(in.read())) {
                case "arrive":
                    doArrive();
                    break;
                case "list bookings":
                    doListBookings();
                    break;
                case "depart":
                    doDepart();
                    break;
                default:
                    System.out.println("no comprende");
                    break;
            }
        }
    }

    private void doListBookings() {
        arrive.out().write("list bookings");
    }

    private void doArrive() {
        if(VacancyService.isFull()){
            System.out.println("Car park is Full ");
        } else {
            arrive.out().write("book");
        }
    }

    private void doDepart() {
        if(VacancyService.isEmpty()){
            System.out.println("Car park is Empty ");
        } else {
            depart.out().write("depart");
        }
    }
}
