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

    // remove, for test
    Customer customer;

    public Booking(AltingChannelInput in) {
        this.in = in;
    }

    @Override
    public void run() {
        customer = new Customer();
        int hashcode = new Object().hashCode();
        while(true){
            String value = String.valueOf(in.read());

            switch(value){
                case "book":
                    makeBooking(hashcode);
                    break;
                case "cancel booking":
                    cancelBooking(hashcode);
                    break;
                default:
                    System.out.println("no comprende");
                    break;
            }
        }
    }

    private void cancelBooking(int hashcode) {
        if(TicketService.onlineTickets.getState() == 0){
            System.out.println("There are no bookings to remove");
        } else{
            // remove, for test
            System.out.println("removing booking number " + hashcode + " " +TicketService.onlineTickets.getState());

            TicketService.remove(hashcode);
            System.out.println("removed booking number " + hashcode + " " +TicketService.onlineTickets.getState());

        }
    }

    private void makeBooking(int hashcode) {
        if(VacancyService.isFull()){
            System.out.println("Sorry there is no vacancy ");
        } else {
            System.out.println("Adding booking number " + hashcode);
            TicketService.onlineTickets.put(hashcode);
            System.out.println(TicketService.onlineTickets.getState());
            //EmailService.sendEmail(Customer)

        }
    }
}
