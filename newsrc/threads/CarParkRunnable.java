package threads;

import processes.CarPark;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class CarParkRunnable extends Thread {
    public void run() {
        new CarPark().run();
    }

}
