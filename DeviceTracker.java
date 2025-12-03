import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.AbstractButton;
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
    private tMouseListener mouseListener = new tMouseListener();

    public DeviceTracker(){
        System.out.println("Layer 3");
        Loader.refresh();
        setLayout(new GridLayout(3,1));
		setBorder(BorderFactory.createLineBorder(Color.cyan));

        String [] columnNames = {"Job ID","Device ID","Type", "Serial Number", "Brand", "Location","Status","Date Received"};
        devTableModel = new NonEditTableMod(columnNames,0);
        devTable = new JTable(devTableModel);
        showTable(Loader.getJobs());
        devTable.setPreferredScrollableViewportSize(new Dimension(1000, Loader.getJobs().size()*15 +20));
        devTable.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(devTable); //Why are you here? I make your table visible :-)

        searchPanel = new JPanel(new GridLayout(3,1));
        searchL = new JLabel("SEARCH BY STATUS/LOCATION");
        searchTF = new JTextField();
        searchButton = new JButton("SEARCH|REFRESH");
        searchPanel.add(searchL);
        searchPanel.add(searchTF);
        searchPanel.add(searchButton);

        subDetailsPanel = new JPanel(new GridLayout(2,2));
        statusL = new JLabel("DEVICE STATUS");
        
        scanBtn = new JButton("SCAN FOR OVERDUE/READY DEVICES");
        updBtn = new JButton("UPDATE");

        statusL.setForeground(Color.black);

        searchButton.addActionListener(buttonListener);
        updBtn.addActionListener(buttonListener);
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
        devTable.addMouseListener(mouseListener);
    }

    private void showTable(ArrayList<Job> jList){
        int counter;
        if (jList.size()>0){ //List size must be greater than 0
            for(counter=0;counter<jList.size();counter++){            
                addToTable(jList.get(counter)); //add first item -upd: 1a. add all Devices
                }
            }
    }

    private void addToTable(Job job){
        String[] item={Integer.toString(job.getJobId()),""+Integer.toString(job.getDevice().getDevID()),""+job.getDevice().getType(),""+ job.getDevice().getSerialNum(),""+job.getDevice().getBrand_ModelInfo(),""+job.getStoragePlace(),""+ job.getDevice().getStatus(),""+job.getDevice().getDate()}; //Add U data to library, each as a string
        devTableModel.addRow(item); //from the model above, make it a new row        
    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(event.getSource()==searchButton){
                ArrayList<Job> filteredDevList = Searcher.devSearcher(Loader.getJobs(), searchTF.getText());
                
                if(filteredDevList.size()>0){
                    devTableModel.setRowCount(0);;
                    showTable(filteredDevList);
                    scrollPane.updateUI();
                    updateUI();
                }else{
                    JOptionPane.showMessageDialog(host, "NO DEVICES NOT FOUND");
                    devTableModel.setRowCount(0);
                    showTable(Loader.getJobs());
                }
            }
            
            else if(event.getSource()==scanBtn){
                for(Job aJob:Loader.getJobs()){
                    if((ChronoUnit.DAYS.between(aJob.getDevice().getDate(), LocalDate.now())>=30)||(aJob.getDevice().getStatus().compareTo("PICKUP READY")==0)){
                        JOptionPane.showMessageDialog(host, aJob.getCustomer().getName()+" HAS NOT PICKED UP THEIR "+aJob.getDevice().getType()+"\nGIVE THEM A CALL AT "+aJob.getCustomer().getNumber());
                    }            
                }       
            }
            
            else if(event.getSource()==updBtn){
                    String devStat ="";
                    java.util.Enumeration<AbstractButton> buttonEnum = radios.getElements();
                    while (buttonEnum.hasMoreElements()) {
                        AbstractButton button = buttonEnum.nextElement();                        
                        if (button.isSelected()) {
                            devStat = button.getText(); // Get the text directly from the JRadioButton/AbstractButton
                            break; 
                        }
                    }
                NonEditTableMod tMod = (NonEditTableMod)devTable.getModel();
                int devId = Integer.parseInt(tMod.getValueAt(devTable.getSelectedRow(), 1).toString());
                int jobListIdx = Searcher.getDevViaId(devId);
                if(jobListIdx!=-1){
                    Loader.getJobs().get(jobListIdx).getDevice().setStatus(devStat);
                    JOptionPane.showMessageDialog(host, "DEVICE STATUS UPDATED TO "+devStat+"!");
                    Writer.writeTo();
                }
            }
        }
    }
    
    private class tMouseListener implements MouseListener{

        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount()==2){
                devTable.setSelectionForeground(Color.red);
                JOptionPane.showMessageDialog(host, "DEVICES LOADED");
                NonEditTableMod tMod = (NonEditTableMod)devTable.getModel();
                String dStat = tMod.getValueAt(devTable.getSelectedRow(), 6).toString();
                statusL.setForeground(Color.red);
                statusL.setText("DEVICE STATUS: "+dStat);   
            }
        }

        public void mouseEntered(MouseEvent e) {
            devTable.setSelectionForeground(Color.red);
        }

        public void mouseExited(MouseEvent e) {
            devTable.setSelectionBackground(Color.black);
        }

        public void mousePressed(MouseEvent e) {
            devTable.setSelectionBackground(Color.cyan);
        }

        public void mouseReleased(MouseEvent e) {
            devTable.setSelectionBackground(Color.white);
        }
    }
}

