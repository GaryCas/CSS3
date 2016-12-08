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

    public CarPark(AltingChannelInput in) {
        this.event = in;
    }

    public void run () {

        One2OneChannel arrive = Channel.one2one();
        One2OneChannel depart = Channel.one2one();


        final Parallel carParkParallel = new Parallel(
                new CSProcess[]{
                        new Arrivals(arrive.in()),
                        new Control(arrive, depart, this.event),
                        new Departs(depart.in())
                }
        );
        new Thread() {
            public void run() {
                carParkParallel.run();
            }
        }.start();
    }
}
