package processes.bookings;

import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannel;
import services.TicketService;
import services.VacancyService;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class BookingComm implements CSProcess {
    private final AltingChannelInput in;
    private final One2OneChannel booking;

    public BookingComm(AltingChannelInput in, One2OneChannel booking) {
        this.in = in;
        this.booking = booking;
    }

    @Override
    public void run() {
        while (true){
            String value = String.valueOf(in.read());

            switch(value){
                case "book":
                    booking.out().write("book");
                    break;
                case "list bookings":
                    booking.out().write("list bookings");
                    break;
                default:
                    System.out.println("no comprende");
                    break;
            }
        }
    }


}
