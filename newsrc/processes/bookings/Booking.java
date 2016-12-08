package processes.bookings;

import entities.Customer;
import entities.CustomerStatus;
import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;
import services.TicketService;
import services.VacancyService;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class Booking implements CSProcess {
    private final ChannelOutput bookingOut;
    private AltingChannelInput bookingIn;

    public Booking(ChannelOutput bookingOut, AltingChannelInput bookingIn) {

        this.bookingOut = bookingOut;
        this.bookingIn = bookingIn;
    }

    @Override
    public void run() {
        while (true){
            String valueFromArrivals = String.valueOf(bookingIn.read());

            String[] value = valueFromArrivals.split(" ");

            switch(value[0]){
                case "book":
                    makeBooking();
                    break;
                case "list":
                    listBookings();
                    break;
                case "depart":
                    doDepart(value[1], value[2]);
                    break;
                default:
                    System.out.println("no comprende");
                    break;
            }

        }
    }

    private void doDepart(String id, String hash) {
        TicketService.remove(Integer.parseInt(hash));
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
