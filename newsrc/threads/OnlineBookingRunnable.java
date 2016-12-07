package threads;

import processes.CarPark;
import processes.OnlineBooking;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class OnlineBookingRunnable extends Thread{
    public void run() {
        new OnlineBooking().run();
    }
}
