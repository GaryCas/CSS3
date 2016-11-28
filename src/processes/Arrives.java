package processes;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInput;

/**
 * Created by rd019985 on 28/11/2016.
 */
public class Arrives implements CSProcess {

    private ChannelInput arrives;

    public Arrives(ChannelInput in){
        arrives = in;
    }

    @Override
    public void run() {
        while(true){
            Object value = arrives.read();

            Control.spaces.put(value);
        }
    }
}
