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
