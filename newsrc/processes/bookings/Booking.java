package processes.bookings;

import entities.Customer;
import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;
import org.jcsp.lang.One2AnyChannel;
import services.TicketService;
import services.VacancyService;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class Booking implements CSProcess {
    private final AltingChannelInput in;
    private final ChannelOutput bookingOut;

    public Booking(AltingChannelInput in, ChannelOutput bookingOut) {
        this.in = in;
        this.bookingOut = bookingOut;
    }

    @Override
    public void run() {
        while (true){
            String value = String.valueOf(in.read());

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

            System.out.println("Adding booking number " + hashcode);
            TicketService.onlineTickets.put(hashcode);
            bookingOut.write("BC:" + customer.getId() + ": Booking confirmed ");
        }
    }


}
