package processes.bookings;

import entities.Customer;
import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;
import services.TicketService;
import services.VacancyService;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class Booking implements CSProcess {
    private AltingChannelInput in;

    public Booking(AltingChannelInput in) {
        this.in = in;
    }

    @Override
    public void run() {

        while(true){
            String value = String.valueOf(in.read()).toLowerCase();

            switch(value){
                case "book":
                    makeBooking();
                    break;
                case "list bookings":
                    listBookings();
                    break;
                default:
                    System.out.println("no comprende");
                    break;
            }
        }
    }

    private void listBookings(){
        TicketService.printBookings();
    }

    private void makeBooking() {
        if(VacancyService.isFull()){
            System.out.println("Sorry we are fully booked ");
        } else {
            int hashcode = new Object().hashCode();
            Customer customer = new Customer();
            customer.sendEmail(hashcode);
            System.out.println("Adding booking number " + hashcode);
            TicketService.onlineTickets.put(hashcode);


        }
    }
}
