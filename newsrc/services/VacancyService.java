package services;

import entities.Customer;
import org.jcsp.util.Buffer;

import java.util.ArrayList;

/**
 * Created by rd019985 on 28/11/2016.
 */
public class VacancyService {

    public static Buffer customers;
    private static ArrayList<Customer> customerArrayList;

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

    // convience method used for the GUI
    public static ArrayList<Customer> getCustomerList(){
        customerArrayList = new ArrayList<>();
        while(!isEmpty()){
            customerArrayList.add((Customer) customers.get());

        }

        for (Customer customer : customerArrayList) {
              customers.put(customer);
        }

        return customerArrayList;
    }


}
