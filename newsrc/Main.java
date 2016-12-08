import guis.BookingsGUI;
import guis.CarParkGUI;
import guis.MailToolGUI;
import org.jcsp.awt.ActiveButton;
import org.jcsp.lang.*;
import org.jcsp.util.OverWriteOldestBuffer;
import services.VacancyService;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class Main {
    public static void main(String args[]) {
        One2AnyChannel bookingToEmailChannel = Channel.one2any();
        Any2OneChannel carParkToBookingChannel = Channel.any2one();

        VacancyService.initCarpark(10);

        new Parallel(
                new CSProcess[]{
                        new CarParkGUI(carParkToBookingChannel),
                        new MailToolGUI(bookingToEmailChannel),
                        new BookingsGUI(bookingToEmailChannel, carParkToBookingChannel)
                }
        ).run();

    }
}
