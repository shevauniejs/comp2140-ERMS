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

public class CustomerTracker extends JPanel{
    private ArrayList <Customer> customers; 
    private JTable cusTable;
    private DefaultTableModel cusTableModel;
    private JScrollPane scrollPane;

    public CustomerTracker(){
        customers = Loader.loadCustomers("Customers.dat");
        String [] columnNames = {"Customer ID","Name", "Phone Number", "Email", "Device","Date Received"};
        cusTableModel = new DefaultTableModel(columnNames,0);
        cusTable = new JTable(cusTableModel);
        showTable(customers);
        cusTable.setPreferredScrollableViewportSize(new Dimension(1000, customers.size()*15 +20));
        cusTable.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(cusTable); //Why are you here?
        add(scrollPane);
        cusTable.setSelectionBackground(Color.yellow);
    }

    private void showTable(ArrayList<Customer> cList){
        int counter;
        if (cList.size()>0){ //List size must be greater than 0
            for(counter=0;counter<cList.size();counter++){            
                addToTable(cList.get(counter)); //add first item -upd: 1a. add all Devices
                }
            }
    }

    private void addToTable(Customer c){
        String[] item={Integer.toString(c.getCusId()),""+c.getName(),""+ c.getNumber(),""+c.getEmail(),""+ c.getDevice().getBrand_ModelInfo(),""+c.getDevice().getDate()}; //Add U data to library, each as a string
        cusTableModel.addRow(item); //from the model above, make it a new row        
    }
/*
    private ArrayList<Customer> loadCustomers(String cusDat){

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
