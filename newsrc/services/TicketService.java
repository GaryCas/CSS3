package services;

import org.jcsp.util.Buffer;

import java.util.ArrayList;

/**
 * Created by rd019985 on 28/11/2016.
 */
public class TicketService {
    public static Buffer onlineTickets = new Buffer(100);
    static ArrayList<Integer> hashcodesToKeep = new ArrayList<>();
    static ArrayList<Integer> hashcodesToThrow = new ArrayList<>();

    public static void remove(int hashcode) {
        while(onlineTickets.getState() != 0){
            if(Integer.parseInt(String.valueOf(onlineTickets.startGet())) != hashcode){
                hashcodesToKeep.add((Integer) onlineTickets.get());
            } else{
                hashcodesToThrow.add((Integer) onlineTickets.get());
            }
        }

        for (Integer hc : hashcodesToKeep) {
            onlineTickets.put(hc);
        }
    }
}
