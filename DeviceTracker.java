import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DeviceTracker extends JPanel{
    private ArrayList <Device> devices = Driver.getData().getDevices(); 
    private JTable devTable;
    private DefaultTableModel devTableModel;

    public DeviceTracker(){
        String [] columnNames = {"Type", "Serial Number", "Brand", "Date Received"};
        devTableModel = new DefaultTableModel(columnNames,0);
        devTable = new JTable(devTableModel);
        showTable(devices);

        devTable.setPreferredScrollableViewportSize(new Dimension(500, devices.size()*15 +50));
        devTable.setFillsViewportHeight(true);

        devTable.setSelectionBackground(Color.yellow);
    }

    private void showTable(ArrayList<Device> dList){
        int counter;
        if (dList.size()>0){ //List size must be greater than 0
            for(counter=0;counter<dList.size();counter++){            
                addToTable(dList.get(counter)); //add first item -upd: 1a. add all persons
                }
            }
    }

    private void addToTable(Device d){
        String[] item={d.getType(),""+ d.getSerialNum(),""+d.getBrand_ModelInfo(),""+d.getDate()}; //Add U data to library, each as a string
        devTableModel.addRow(item); //from the model above, make it a new row        

    }
}
