package processes.email;

import entities.Customer;
import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannel;
import services.VacancyService;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class UINT implements CSProcess{

    private AltingChannelInput in;
    private final One2OneChannel dispatch;
    private final One2OneChannel mailBag;

    public UINT(AltingChannelInput in, One2OneChannel dispatch, One2OneChannel mailBag){
        this.in = in;
        this.dispatch = dispatch;
        this.mailBag = mailBag;
    }

    @Override
    public void run() {
        VacancyService.customers.put(new Customer());

        while (true) {
            String value = String.valueOf(in.read());

            switch (value) {
                case "Mail Bag":
                    // start up a GUI with the buttons next, prev, delete and send.m
                    // Make send.m link to dispatch
                    mailBag.out().write("show");
                    break;
                case "dispatch":
                    dispatch.out().write(VacancyService.customers.startGet());
                    break;
                default:
                    System.out.println("no comprende");
                    break;
            }
        }
    }
}
