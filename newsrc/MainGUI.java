
import guis.BookingsGUI;
import guis.CarParkGUI;
import guis.MailToolGUI;
import javafx.event.EventDispatcher;
import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;
import processes.MailTool;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class MainGUI implements CSProcess {
    private AltingChannelInput in;

    public MainGUI(AltingChannelInput in) {
        this.in = in;
    }

    @Override
    public void run() {
        while(true){
            String value = String.valueOf(in.read());

            switch(value){
                case "CarPark":
                    new CarParkGUI().run();
                    break;
                case "MailTool":
                    new MailToolGUI().run();
                    break;
                case "OnlineBooking":
                    new BookingsGUI().run();
                    break;
                default:
                    System.out.println("no comprende");
                    break;
            }
        }
    }
}
