import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CustomerTracker extends JPanel{
    private JPanel host = this;
    private JTable cusTable;
    private DefaultTableModel cusTableModel;
    private JScrollPane scrollPane;
    
    private JLabel searchL, nameL, numberL, emailL;
    private JTextField searchTF, nameTF, numberTF, emailTF;
    private JPanel searchPanel, cusDetailsPanel;
    private JButton searchButton, updBtn;

    private ButtonListener buttonListener = new ButtonListener();
    private tMouseListener clickListener = new tMouseListener();
    

    public CustomerTracker(){
        System.out.println("Layer 3");
        setLayout(new GridLayout(3,1));
        setBorder(BorderFactory.createLineBorder(Color.cyan));
        
        String [] columnNames = {"Customer ID","Name", "Phone Number", "Email", "Device","Date Received"};
        cusTableModel = new NonEditTableMod(columnNames,0);
        cusTable = new JTable(cusTableModel);
        showTable(Loader.getJobs());
        cusTable.setPreferredScrollableViewportSize(new Dimension(1000, Loader.getJobs().size()*15 +20));
        cusTable.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(cusTable); //Why are you here? I make your table visible :-)     
        
        cusTable.addMouseListener(clickListener);
        
        searchPanel = new JPanel(new GridLayout(3,1));
        searchL = new JLabel("SEARCH BY NAME, EMAIL OR NUMBER");
        searchTF = new JTextField();
        searchButton = new JButton("SEARCH|REFRESH");

        searchButton.addActionListener(buttonListener);

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
        updBtn.addActionListener(buttonListener);
        
        cusDetailsPanel.add(nameL, null, 0);
        cusDetailsPanel.add(nameTF, null, 1);
        cusDetailsPanel.add(numberL, null, 2);
        cusDetailsPanel.add(numberTF, null, 3);
        cusDetailsPanel.add(emailL, null, 4);
        cusDetailsPanel.add(emailTF, null, 5);
        cusDetailsPanel.add(updBtn, null, 6);
        cusDetailsPanel.setPreferredSize(new Dimension(10,100));

        add(scrollPane);
        add(cusDetailsPanel);
        add(searchPanel);

        cusTable.setSelectionBackground(Color.yellow);
    }

    private void showTable(ArrayList<Job> jList){
        int counter;
        if (jList.size()>0){ //List size must be greater than 0
            for(counter=0;counter<jList.size();counter++){            
                addToTable(jList.get(counter)); //add first item -upd: 1a. add all Devices
                }
            }
    }

    private void showTable(Job j){
        addToTable(j);
    }

    private void addToTable(Job j){
        String[] item={Integer.toString(j.getCustomer().getCusId()),""+j.getCustomer().getName(),""+ j.getCustomer().getNumber(),""+j.getCustomer().getEmail(),""+ j.getCustomer().getDevice().getBrand_ModelInfo(),""+j.getCustomer().getDevice().getDate()}; //Add U data to library, each as a string
        cusTableModel.addRow(item); //from the model above, make it a new row        
    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(event.getSource()==searchButton){
                System.out.println("BUTTON CLICKED");
                Job filteredCust = Searcher.cusSearcher(Loader.getJobs(), searchTF.getText());
                ArrayList<Job> filteredCustList = Searcher.cusSearcher(Loader.getJobs(),null,searchTF.getText());
                if(filteredCust!= null){
                    cusTableModel.setRowCount(0);
                    addToTable(filteredCust);
                }else if(filteredCustList.size()!=0){
                    cusTableModel.setRowCount(0);
                    showTable(filteredCustList);
                }else{
                    JOptionPane.showMessageDialog(host,"CUSTOMER NOT FOUND");
                }
                searchTF.setText("");
                showTable(filteredCust);
                scrollPane.updateUI();
                updateUI();
            }
            else if(event.getSource()==updBtn){
                NonEditTableMod tMod = (NonEditTableMod)cusTable.getModel();
                int cId = Integer.parseInt(tMod.getValueAt(cusTable.getSelectedRow(), 0).toString());
                int jobListIdx = Searcher.getCusViaId(cId);
                if(jobListIdx!=-1){
                    Loader.getJobs().get(jobListIdx).getCustomer().setName(nameTF.getText());
                    Loader.getJobs().get(jobListIdx).getCustomer().setEmail(emailTF.getText());
                    Loader.getJobs().get(jobListIdx).getCustomer().setNumber(numberTF.getText());
                    JOptionPane.showMessageDialog(host, "CUSTOMER INFORMATION UPDATED!");
                    Writer.writeTo();
                }
            }
        }
    }
    
    private class tMouseListener implements MouseListener{
            
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    cusTable.setSelectionForeground(Color.red);
                    JOptionPane.showMessageDialog(host, "CUSTOMER LOADED");
                    NonEditTableMod tMod = (NonEditTableMod)cusTable.getModel();
                    String cName = tMod.getValueAt(cusTable.getSelectedRow(), 1).toString();
                    String cPhoneNumber = tMod.getValueAt(cusTable.getSelectedRow(), 2).toString();
                    String cEmail = tMod.getValueAt(cusTable.getSelectedRow(), 3).toString();
                    

                    nameTF.setText(cName);
                    numberTF.setText(cPhoneNumber);
                    emailTF.setText(cEmail);
                }
            }

            public void mouseEntered(MouseEvent e) {
                cusTable.setSelectionForeground(Color.black);
            }

            public void mouseExited(MouseEvent e) {
                cusTable.setSelectionBackground(Color.white);
            }

            public void mousePressed(MouseEvent e) {
                cusTable.setSelectionBackground(Color.cyan);
            }

            public void mouseReleased(MouseEvent e) {
                cusTable.setSelectionBackground(Color.white);
            }
            
        }

}
