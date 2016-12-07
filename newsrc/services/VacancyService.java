package services;

import org.jcsp.util.Buffer;

/**
 * Created by rd019985 on 28/11/2016.
 */
public class VacancyService {

    public static Buffer customers;

    public static void initCarpark(int cap){
        customers = new Buffer(cap -1);
    }

    public static boolean isFull(){
        if (customers.getState() == 2){
            return true;
        }
        return false;
    }

    public static boolean isEmpty(){
        if (customers.getState() == 0){
            return true;
        }
        return false;
    }


}
