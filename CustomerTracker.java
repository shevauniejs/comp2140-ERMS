import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomerTracker extends JPanel{
    private ArrayList <Customer> customers; 
    private JTable cusTable;
    private DefaultTableModel cusTableModel;
    private JScrollPane scrollPane;
    private JLabel titleLabel;

    public CustomerTracker(){
        titleLabel = new JLabel("CUSTOMERS");
        customers = Loader.loadCustomers("Customers.dat");
        String [] columnNames = {"Customer ID","Name", "Phone Number", "Email", "Device","Date Received"};
        cusTableModel = new DefaultTableModel(columnNames,0);
        cusTable = new JTable(cusTableModel);
        showTable(customers);
        cusTable.setPreferredScrollableViewportSize(new Dimension(1000, customers.size()*15 +20));
        cusTable.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(cusTable); //Why are you here? I make your table visible :-)
        add(titleLabel);
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

}
