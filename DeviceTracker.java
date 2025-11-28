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

public class DeviceTracker extends JPanel{
    private ArrayList <Device> devices; 
    private JTable devTable;
    private DefaultTableModel devTableModel;
    private JScrollPane scrollPane;

    public DeviceTracker(){
        devices = loadDevices("Devices.dat");
        String [] columnNames = {"Device ID","Type", "Serial Number", "Brand", "Status","Date Received"};
        devTableModel = new DefaultTableModel(columnNames,0);
        devTable = new JTable(devTableModel);
        showTable(devices);
        devTable.setPreferredScrollableViewportSize(new Dimension(500, devices.size()*15 +50));
        devTable.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(devTable); //Why are you here?
        add(scrollPane);
        devTable.setSelectionBackground(Color.yellow);
    }

    private void showTable(ArrayList<Device> dList){
        int counter;
        if (dList.size()>0){ //List size must be greater than 0
            for(counter=0;counter<dList.size();counter++){            
                addToTable(dList.get(counter)); //add first item -upd: 1a. add all Devices
                }
            }
    }

    private void addToTable(Device d){
        String[] item={Integer.toString(d.getDevID()),""+d.getType(),""+ d.getSerialNum(),""+d.getBrand_ModelInfo(),""+ d.getStatus(),""+d.getDate()}; //Add U data to library, each as a string
        devTableModel.addRow(item); //from the model above, make it a new row        
    }

    private ArrayList<Device> loadDevices(String devDat){

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

                Device dev = new Device(devId, serial, brand, type, status, date); //make new Device from read from file
                devList.add(dev); //add new Device to ArrayList
            }
            dev_scan.close(); //close scanner
        }
        catch(IOException e)
        {}
        return  devList; //return the ArrayList
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
}
