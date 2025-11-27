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

    public static ArrayList<Job> getJobs(){
        return jobs;
    }
    public static ArrayList<Device> getDevices(){
        return devices;
    }    
    public static ArrayList<Customer> getCustomers(){
        return customers;
    }
}
