import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Parallel;
import processes.CarPark;
import processes.MailTool;
import processes.OnlineBooking;
import threads.CarParkRunnable;
import threads.MailToolRunnable;
import threads.OnlineBookingRunnable;

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

            Parallel mainGUI = new Parallel(
                    new CSProcess[]{
                        new CarPark(),
                        new MailTool(),
                        new OnlineBooking()
                    }
            );

            mainGUI.run();

            switch(value){
                case "CarPark":
                    new CarParkRunnable().start();
                    break;
                case "MailTool":
                    new MailToolRunnable().start();
                    break;
                case "OnlineBooking":
                    new OnlineBookingRunnable().start();
                    break;
                default:
                    System.out.println("no comprende");
                    break;
            }
        }
    }
}
