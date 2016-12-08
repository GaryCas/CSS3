package processes.bookings;

import entities.Customer;
import entities.CustomerStatus;
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
        int hashcode = new Object().hashCode();
        Customer customer = new Customer();
        customer.setTicketHash(hashcode);

        if(VacancyService.isFull()){
            System.out.println("Sorry we are fully booked " + customer.getId());
            customer.setStatus(CustomerStatus.BOOKINGFAILED);
            bookingOut.write(customer);
        } else {
            System.out.println("Booking successful for " + customer.getId());
            customer.setStatus(CustomerStatus.BOOKED);

            VacancyService.customers.put(customer);
            TicketService.onlineTickets.put(hashcode);
            bookingOut.write(customer);
        }
    }


}
