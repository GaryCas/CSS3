package processes;

import org.jcsp.lang.*;
import processes.carpark.Arrivals;
import processes.carpark.Control;
import processes.carpark.Departs;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class CarPark implements CSProcess {


    private AltingChannelInput event;
    private Any2OneChannel carParkToBookingChannel;

    public CarPark(AltingChannelInput in, Any2OneChannel carParkToBookingChannel) {
        this.event = in;
        this.carParkToBookingChannel = carParkToBookingChannel;
    }

    public void run () {

        One2OneChannel arrive = Channel.one2one();
        One2OneChannel depart = Channel.one2one();


        final Parallel carParkParallel = new Parallel(
                new CSProcess[]{
                        new Arrivals(arrive.in(), carParkToBookingChannel),
                        new Control(arrive, depart, event),
                        new Departs(depart.in(), carParkToBookingChannel)
                }
        );
        new Thread() {
            public void run() {
                carParkParallel.run();
            }
        }.start();
    }
}
