import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Loader {


    public Loader(){}

    public static ArrayList<Device> loadDevices(String devDat){

        Scanner dev_scan = null; //Scanner to read from source
        ArrayList<Device>   devList = new ArrayList<Device>(); //ArrayList to store Devices
        try
        {
            dev_scan  = new Scanner(new File(devDat)); //dev_scan reads from file
            while(dev_scan.hasNext()) //if the file has data
            {
                String [] nextLine = dev_scan.nextLine().split("="); //split next line by ' ', add each string to array
                int devId = Integer.parseInt(nextLine[0]); System.out.println(devId);
                String type = nextLine[1];  //System.out.println(type);
                String serial = nextLine[2]; //System.out.println(serial);
                String brand = nextLine[3]; //System.out.println(brand);
                String status = nextLine[4]; //System.out.println(status);
                Date date = toDate(nextLine[5]); //System.out.println(date);

                Device dev = new Device(devId, type, serial, brand, status, date); //make new Device from read from file
                devList.add(dev); //add new Device to ArrayList
            }
            dev_scan.close(); //close scanner
        }
        catch(IOException e)
        {}
        return  devList; //return the ArrayList
    }

    public static ArrayList<Job> loadJobs(String jobDat){

        Scanner job_scan = null; //Scanner to read from source
        ArrayList<Job>   jobList = new ArrayList<Job>(); //ArrayList to store JobsloadJobs
        try
        {
            job_scan  = new Scanner(new File(jobDat)); //dev_scan reads from file
            while(job_scan.hasNext()) //if the file has data
            {
                String [] nextLine = job_scan.nextLine().split("="); //split next line by ' ', add each string to array
                int jobId = Integer.parseInt(nextLine[0]); //System.out.println("JOB ID: "+jobId);
                int cusId = Integer.parseInt(nextLine[1]); //System.out.println("CUS ID: "+cusId);
                String cusName = nextLine[2];  //System.out.println("CUS NAME: "+cusName);
                String cusNum = nextLine[3]; //System.out.println("CUS NUM: "+cusNum);
                String cusEmail = nextLine[4]; //System.out.println("CUS EMAIL: "+cusEmail);

                int devId = Integer.parseInt(nextLine[5]); //System.out.println("DEV ID: "+devId);
                String type = nextLine[6];  //System.out.println("DEV TYPE: "+type);
                String serial = nextLine[7]; //System.out.println("DEV SERIAL: "+serial);
                String brand = nextLine[8]; //System.out.println("DEV BRAND: "+brand);
                String status = nextLine[9]; //System.out.println("DEV STATUS: "+status);
                Date date = toDate(nextLine[10]);

                
                String issue = nextLine[11]; //System.out.println("JOB ISSUE: "+issue);
                String diag = nextLine[12]; //System.out.println("JOB DIAG: "+diag);
                String notes = nextLine[13]; //System.out.println("JOB NOTES: "+notes);
                String loc = nextLine[14]; //System.out.println("JOB LOCATION: "+loc);
                double cost = Double.parseDouble(nextLine[15]); //System.out.println("JOB COST: "+cost);
                double amount = Double.parseDouble(nextLine[16]); //System.out.println("JOB AMOUNT: "+amount);
                
                Device dev = new Device(devId, type, serial, brand, status, date);
                Customer cust = new Customer(cusId, cusName, cusNum, cusEmail, dev);
                Job job = new Job(jobId, cust, issue, diag, notes, loc, cost, amount); //make new Device from read from file
                jobList.add(job); //add new Device to ArrayList
            }
            job_scan.close(); //close scanner
        }
        catch(IOException e)
        {}
        return  jobList; //return the ArrayList
    }
    
    public static ArrayList<Customer> loadCustomers(String cusDat){

        Scanner cus_scan = null; //Scanner to read from source
        ArrayList<Customer>   cusList = new ArrayList<Customer>(); //ArrayList to store Devices
        try
        {
            cus_scan  = new Scanner(new File(cusDat)); //dev_scan reads from file
            while(cus_scan.hasNext()) //if the file has data
            {
                String [] nextLine = cus_scan.nextLine().split("="); //split next line by ' ', add each string to array
                int cusId = Integer.parseInt(nextLine[0]);
                String cusName = nextLine[1];  
                String cusNum = nextLine[2]; 
                String cusEmail = nextLine[3]; 

                int devId = Integer.parseInt(nextLine[4]); 
                String type = nextLine[5];  
                String serial = nextLine[6]; 
                String brand = nextLine[7]; 
                String status = nextLine[8]; 
                Date date = toDate(nextLine[9]); 

                Device dev = new Device(devId, type, serial, brand, status, date);
                Customer cus = new Customer(cusId, cusName, cusNum, cusEmail, dev); //make new Device from read from file
                cusList.add(cus); //add new Device to ArrayList
            }
            cus_scan.close(); //close scanner
        }
        catch(IOException e)
        {}
        return  cusList; //return the ArrayList
    }
    
    public static Date toDate(String dateString){
        String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
        Date dateObject = null;
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.US);
        try {
             dateObject = formatter.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Error parsing date: The string format did not match the pattern.");
            e.printStackTrace();
        }
        return dateObject;
    }
}
