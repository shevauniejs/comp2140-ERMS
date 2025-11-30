import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DeviceTracker extends JPanel{
    private JPanel host = this;
    private ArrayList <Device> devices = new ArrayList<Device>(); 
    private ArrayList<Job> jobs;
    private JTable devTable;
    private DefaultTableModel devTableModel;
    
    private JScrollPane scrollPane;
    private JPanel searchPanel, subDetailsPanel, radPan;
    private JLabel searchL, statusL;
    private JTextField searchTF;
    private JButton searchButton, updBtn, scanBtn;

    private ButtonGroup radios;
    private JRadioButton pending, inprogress, complete, awaitingparts, pickup, collected;
    private ButtonListener buttonListener = new ButtonListener();

    public DeviceTracker(){
        setLayout(new GridLayout(3,1));
		setBorder(BorderFactory.createLineBorder(Color.cyan));

        jobs = Loader.loadJobs("Jobs.dat");
        for(Job aJob:jobs){
            devices.add(aJob.getDevice());           
        }

        String [] columnNames = {"Device ID","Type", "Serial Number", "Brand", "Status","Date Received"};
        devTableModel = new NonEditTableMod(columnNames,0);
        devTable = new JTable(devTableModel);
        showTable(devices);
        devTable.setPreferredScrollableViewportSize(new Dimension(1000, devices.size()*15 +20));
        devTable.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(devTable); //Why are you here? I make your table visible :-)

        searchPanel = new JPanel(new GridLayout(3,1));
        searchL = new JLabel("SEARCH BY STATUS");
        searchTF = new JTextField();
        searchButton = new JButton("SEARCH");
        searchPanel.add(searchL);
        searchPanel.add(searchTF);
        searchPanel.add(searchButton);

        searchButton.addActionListener(buttonListener);

        subDetailsPanel = new JPanel(new GridLayout(2,2));
        statusL = new JLabel("DEVICE STATUS");
        scanBtn = new JButton("SCAN FOR OVERDUE/READY DEVICES");
        updBtn = new JButton("UPDATE");
       
        scanBtn.addActionListener(buttonListener);

        radios = new ButtonGroup();
        pending = new JRadioButton("PENDING");
        inprogress = new JRadioButton("IN PROGRESS");
        awaitingparts = new JRadioButton("AWAITING PARTS");
        complete = new JRadioButton("REPAIR COMPLETE");
        pickup = new JRadioButton("PICKUP READY");
        collected = new JRadioButton("COLLECTED");

        radios.add(pending);
        radios.add(awaitingparts);
        radios.add(inprogress);
        radios.add(complete);
        radios.add(pickup);
        radios.add(collected);

        radPan = new JPanel();
        radPan.add(awaitingparts);
        radPan.add(inprogress);
        radPan.add(complete);
        radPan.add(pickup);
        radPan.add(collected);

        subDetailsPanel.add(statusL, null, 0);
        subDetailsPanel.add(radPan, null, 1);
        subDetailsPanel.add(updBtn, null, 2);
        subDetailsPanel.add(scanBtn, null, 3);
        subDetailsPanel.setPreferredSize(new Dimension(10,150));

        add(scrollPane);
        add(subDetailsPanel);
        add(searchPanel);
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

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(event.getSource()==searchButton){
                System.out.println("BUTTON CLICKED");
                ArrayList<Device> filteredDevList = Searcher.devSearcher(devices, searchTF.getText());
                
                if(filteredDevList.size()>0){
                    devTableModel.setRowCount(0);;
                    showTable(filteredDevList);
                    scrollPane.updateUI();
                    updateUI();
                }else{
                    JOptionPane.showMessageDialog(host, "NO DEVICES NOT FOUND");
                    devTableModel.setRowCount(0);
                    showTable(devices);
                }
            }
            else if(event.getSource()==scanBtn){
                for(Job aJob:jobs){
                    if((ChronoUnit.DAYS.between(aJob.getDevice().getDate(), LocalDate.now())>=30)||(aJob.getDevice().getStatus().compareTo("READY")==0)){
                        JOptionPane.showMessageDialog(host, aJob.getCustomer().getName()+" HAS NOT PICKED UP THEIR "+aJob.getDevice().getType()+"\nGIVE THEM A CALL AT "+aJob.getCustomer().getNumber());
            }            
        }       
            }
        }
    }
}
