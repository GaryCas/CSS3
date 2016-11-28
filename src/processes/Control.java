package processes;

import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInput;
import org.jcsp.util.Buffer;
import processes.carpark.Arrives;
import processes.carpark.Departs;

/**
 * Created by rd019985 on 28/11/2016.
 */
public class Control implements CSProcess {
    private Any2OneChannel departChannel;
    private Any2OneChannel arrivesChannel;
    private Arrives arrives;
    private Departs departs;
    public static Buffer spaces;
    public static Buffer onlineTickets;
    int i = 0;

    private ChannelInput input;

    public Control(ChannelInput in, Any2OneChannel arrivesChannel, Any2OneChannel departsChannel){
        this.input = in;

        this.arrivesChannel = arrivesChannel;
        this.departChannel = departsChannel;

        arrives = new Arrives(arrivesChannel.in());
        departs = new Departs(departsChannel.in());
    }

    @Override
    public void run() {
        initCarpark(2);
        while(true){
            switch (String.valueOf(input.read())){
                case "arrive":
                    doArrive();
                    break;
                case "depart":
                    doDepart();
                    break;
            }
        }
    }


    private void doArrive() {
        if (isFull()){
            System.out.println("Sorry, the car park is full");
        } else{
            arrivesChannel.out().write(i++);
        }
    }

    private void doDepart() {
        if (isEmpty()){
            System.out.println("There are no cars, m8");
        } else{
            departChannel.out().write(i--);

        }
    }

    public static boolean isFull(){
        if (spaces.getState() == 2){
            return true;
        }
        return false;
    }

    public static boolean isEmpty(){
        if (spaces.getState() == 0){
            return true;
        }
        return false;
    }

    public void initCarpark(int cap){
        spaces = new Buffer(cap -1);
    }

}
