import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class Testi implements CSProcess {

    private AltingChannelInput in;

    public Testi(AltingChannelInput in) {
        this.in = in;
    }


    @Override
    public void run() {
        while(true) {
            switch (String.valueOf(in.read())) {
                case "arrive":
                    System.out.println("arrive");
                    break;
                case "depart":
                    System.out.println("depart");
                    break;
                default:
                    System.out.println("no comprende");
                    break;
            }
        }
    }
}
