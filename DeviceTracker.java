import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DeviceTracker extends JPanel{
    private ArrayList <Device> devices; 
    private JTable devTable;
    private DefaultTableModel devTableModel;
    private JScrollPane scrollPane;
    private JLabel titleLabel;

    public DeviceTracker(){
        titleLabel = new JLabel("DEVICE TRACKER");
        devices = Loader.loadDevices("Devices.dat");
        String [] columnNames = {"Device ID","Type", "Serial Number", "Brand", "Status","Date Received"};
        devTableModel = new DefaultTableModel(columnNames,0);
        devTable = new JTable(devTableModel);
        showTable(devices);
        devTable.setPreferredScrollableViewportSize(new Dimension(1000, devices.size()*15 +20));
        devTable.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(devTable); //Why are you here? I make your table visible :-)
        add(titleLabel);
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

}
