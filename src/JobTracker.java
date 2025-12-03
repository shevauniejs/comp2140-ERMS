import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**Job tracker redundant, replaced by Device tracker
 * @author Shevaunie Senior
 */

public class JobTracker extends JPanel{
    private JPanel host = this;
    private JTable devTable;
    private DefaultTableModel devTableModel;
    private JScrollPane scrollPane;
    private JLabel searchL, notesL;
    private JPanel searchPanel, subDetailsPanel;
    private JTextField searchTF, notesTF;
    private JButton searchButton, updBtn;
    private ButtonListener btnListener = new ButtonListener();
    private tMouseListener clickListener = new tMouseListener();

    public JobTracker(){
        System.out.println("Layer 3");
        Loader.refresh();
        setLayout(new GridLayout(3,1));
        setBorder(BorderFactory.createLineBorder(Color.cyan));

        String [] columnNames = {"Job ID","Customer Name", "Device Brand/Model", "Location","Issue", "Notes","Diagnosis","Status"};
        devTableModel = new NonEditTableMod(columnNames,0);
        devTable = new JTable(devTableModel);
        showTable(Loader.getJobs());
        devTable.setPreferredScrollableViewportSize(new Dimension(1000, Loader.getJobs().size()*15 +20));
        devTable.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(devTable); //Why are you here? I make your table visible :-)
        devTable.addMouseListener(clickListener);
        
        searchPanel = new JPanel(new GridLayout(3,1));
        searchL = new JLabel("FILTER BY STATUS");
        searchTF = new JTextField();
        searchButton = new JButton("FILTER|DISPLAY JOBS");
        searchPanel.add(searchL);
        searchPanel.add(searchTF);
        searchPanel.add(searchButton);
        searchButton.addActionListener(btnListener);

        subDetailsPanel = new JPanel(new GridLayout(3,1));
        notesL = new JLabel("NOTES");
        notesTF = new JTextField();
        updBtn = new JButton("UPDATE");

        
        subDetailsPanel.add(notesL, null, 0);
        subDetailsPanel.add(notesTF, null, 1);
        subDetailsPanel.add(updBtn, null, 2);
        subDetailsPanel.setPreferredSize(new Dimension(10,100));

        add(scrollPane);
        add(searchPanel);
        add(subDetailsPanel);
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
        String[] item={Integer.toString(j.getJobId()),""+j.getCustomer().getName(),""+ j.getDevice().getBrand_ModelInfo(),""+j.getStoragePlace(),""+j.getIssue(),""+ j.getNotes(),""+j.getDiagnosis(),""+j.getDevice().getStatus()}; //Add U data to library, each as a string
        devTableModel.addRow(item); //from the model above, make it a new row        
    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(event.getSource()==searchButton){
                System.out.println("BUTTON CLICKED");
                ArrayList<Job> filteredJobList = Searcher.jobSearcher(Loader.getJobs(), searchTF.getText());
                    if(filteredJobList.size()!=0){
                        devTableModel.setRowCount(0);
                        showTable(filteredJobList);
                        scrollPane.updateUI();
                        updateUI();
                    }else{
                        JOptionPane.showMessageDialog(host, "JOB NOT FOUND");
                        devTableModel.setRowCount(0);
                        showTable(Loader.getJobs());
                    }
                    searchTF.setText("");
            }
        }
    }

    private class tMouseListener implements MouseListener{

        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount()==2){
                devTable.setSelectionForeground(Color.red);
                JOptionPane.showMessageDialog(host, "CLICKED");
            }
        }

        public void mouseEntered(MouseEvent e) {
            devTable.setSelectionForeground(Color.black);
        }

        public void mouseExited(MouseEvent e) {
            devTable.setSelectionBackground(Color.white);
        }

        public void mousePressed(MouseEvent e) {
            devTable.setSelectionBackground(Color.cyan);
        }

        public void mouseReleased(MouseEvent e) {
            devTable.setSelectionBackground(Color.white);
        }
        
    }
}
