package threads;

import processes.CarPark;

/**
 * Created by rd019985 on 07/12/2016.
 */
public class MailToolRunnable extends Thread {
    public void run() {
        new MailToolRunnable().run();
    }
}
