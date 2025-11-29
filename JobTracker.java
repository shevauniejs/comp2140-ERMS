import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JobTracker extends JPanel{
    private ArrayList <Job> jobs; 
    private JTable devTable;
    private DefaultTableModel devTableModel;
    private JScrollPane scrollPane;
    private JLabel titleLabel;

    public JobTracker(){
        titleLabel = new JLabel("JOBS");
        jobs =Loader.loadJobs("Jobs.dat");
        String [] columnNames = {"Job ID","Customer Name", "Device Brand/Model", "Issue", "Notes","Diagnosis","Status"};
        devTableModel = new DefaultTableModel(columnNames,0);
        devTable = new JTable(devTableModel);
        showTable(jobs);
        devTable.setPreferredScrollableViewportSize(new Dimension(1000, jobs.size()*15 +20));
        devTable.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(devTable); //Why are you here? I make your table visible :-)

        
        searchPanel = new JPanel(new GridLayout(3,1));
        searchL = new JLabel("SEARCH BY NAME, NUMBER OR ID");
        searchTF = new JTextField();
        searchButton = new JButton("SEARCH");
        searchPanel.add(searchL);
        searchPanel.add(searchTF);
        searchPanel.add(searchButton);

        cusDetailsPanel = new JPanel(new GridLayout(4,2));
        nameL = new JLabel("NAME");
        numberL = new JLabel("NUMBER");
        emailL = new JLabel("EMAIL");
        nameTF = new JTextField();
        numberTF = new JTextField();
        emailTF = new JTextField();
        updBtn = new JButton("UPDATE");

        
        cusDetailsPanel.add(nameL, null, 0);
        cusDetailsPanel.add(nameTF, null, 1);
        cusDetailsPanel.add(numberL, null, 2);
        cusDetailsPanel.add(numberTF, null, 3);
        cusDetailsPanel.add(emailL, null, 4);
        cusDetailsPanel.add(emailTF, null, 5);
        cusDetailsPanel.add(updBtn, null, 6);
        cusDetailsPanel.setPreferredSize(new Dimension(10,100));





        add(titleLabel);
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

}
