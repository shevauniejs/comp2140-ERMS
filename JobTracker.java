import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JobTracker extends JPanel{
    private ArrayList <Job> jobs; 
    private JTable devTable;
    private DefaultTableModel devTableModel;
    private JScrollPane scrollPane;

    public JobTracker(){
        jobs =Loader.loadJobs("Jobs.dat");
        String [] columnNames = {"Job ID","Customer Name", "Device Brand/Model", "Issue", "Notes","Diagnosis","Status"};
        devTableModel = new DefaultTableModel(columnNames,0);
        devTable = new JTable(devTableModel);
        showTable(jobs);
        devTable.setPreferredScrollableViewportSize(new Dimension(1000, jobs.size()*15 +20));
        devTable.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(devTable); //Why are you here?
        add(scrollPane);
        devTable.setSelectionBackground(Color.yellow);
    }

    private void showTable(ArrayList<Job> jList){
        int counter;
        if (jList.size()>0){ //List size must be greater than 0
            for(counter=0;counter<jList.size();counter++){            
                addToTable(jList.get(counter)); //add first item -upd: 1a. add all JobsloadJobs
                }
            }
    }

    private void addToTable(Job j){
        String[] item={Integer.toString(j.getJobId()),""+j.getCustomer().getName(),""+ j.getDevice().getBrand_ModelInfo(),""+j.getIssue(),""+ j.getNotes(),""+j.getDiagnosis(),""+j.getDevice().getStatus()}; //Add U data to library, each as a string
        devTableModel.addRow(item); //from the model above, make it a new row        
    }
/*
    private ArrayList<Job> loadJobs(String jobDat){

        Scanner job_scan = null; //Scanner to read from source
        ArrayList<Job>   jobList = new ArrayList<Job>(); //ArrayList to store JobsloadJobs
        try
        {
            job_scan  = new Scanner(new File(jobDat)); //dev_scan reads from file
            while(job_scan.hasNext()) //if the file has data
            {
                String [] nextLine = job_scan.nextLine().split("="); //split next line by ' ', add each string to array
                System.out.println("Length"+nextLine.length);
                int jobId = Integer.parseInt(nextLine[0]); System.out.println("JOB ID: "+jobId);
                int cusId = Integer.parseInt(nextLine[1]); System.out.println("CUS ID: "+cusId);
                String cusName = nextLine[2];  System.out.println("CUS NAME: "+cusName);
                String cusNum = nextLine[3]; System.out.println("CUS NUM: "+cusNum);
                String cusEmail = nextLine[4]; System.out.println("CUS EMAIL: "+cusEmail);

                int devId = Integer.parseInt(nextLine[5]); System.out.println("DEV ID: "+devId);
                String type = nextLine[6];  System.out.println("DEV TYPE: "+type);
                String serial = nextLine[7]; System.out.println("DEV SERIAL: "+serial);
                String brand = nextLine[8]; System.out.println("DEV BRAND: "+brand);
                String status = nextLine[9]; System.out.println("DEV STATUS: "+status);
                Date date = toDate(nextLine[10]); System.out.println("DEV DATE: "+date);
                
                String issue = nextLine[11]; System.out.println("JOB ISSUE: "+issue);
                String diag = nextLine[12]; System.out.println("JOB DIAG: "+diag);
                String notes = nextLine[13]; System.out.println("JOB NOTES: "+notes);
                String loc = nextLine[14]; System.out.println("JOB LOCATION: "+loc);
                double cost = Double.parseDouble(nextLine[15]); System.out.println("JOB COST: "+cost);
                double amount = Double.parseDouble(nextLine[16]); System.out.println("JOB AMOUNT: "+amount);
                
                Device dev = new Device(devId, type, serial, brand,status, date);
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

    private Date toDate(String dateString){
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
 */
}
