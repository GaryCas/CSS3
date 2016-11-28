package processes.carpark;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInput;
import processes.Control;

/**
 * Created by rd019985 on 28/11/2016.
 */
public class Departs implements CSProcess {

    private ChannelInput depart;

    public Departs(ChannelInput in){
        this.depart = in;
    }

    @Override
    public void run() {
        while(true){
            Object value = depart.read();
            Control.spaces.get();
        }
    }
}
