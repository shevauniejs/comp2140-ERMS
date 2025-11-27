import java.util.ArrayList;

public class DataClass {
    static ArrayList<Customer> customers;
    static ArrayList<Device> devices;
    static ArrayList<Job> jobs;

    public DataClass(){
        customers = new ArrayList<Customer>();
        devices = new ArrayList<Device>();
        jobs = new ArrayList<Job>();
    }

}
