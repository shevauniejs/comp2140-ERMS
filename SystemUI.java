import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;


public class SystemUI extends JFrame{
    JFrame UI = this;
    JPanel optionsPanel;
    JPanel detailsPanel;
    JPanel mainPanel;

    JButton addJob;
    JButton viewJob;
    JButton viewDevices;
    JButton viewCust;
    JButton listPayments;

    JButton submitData;


    public SystemUI(){
        UI.setDefaultCloseOperation(EXIT_ON_CLOSE);
        UI.setPreferredSize(new Dimension(1024,768)); //frame size

        optionsPanel = new JPanel(new GridLayout(5,1)); //left side has options to add job etc
        detailsPanel = new JPanel(); //right side shows details and fields

        optionsPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        detailsPanel.setBorder(BorderFactory.createLineBorder(Color.blue));

        optionsPanel.setPreferredSize(new Dimension(300,720));
        detailsPanel.setPreferredSize(new Dimension(700,720));

        addJob = new JButton("Add Job");
        viewJob = new JButton("View Job");
        viewDevices = new JButton("View Devices");
        viewCust = new JButton("View Customers");
        listPayments = new JButton("Show Payments");

        addJob.addActionListener(new ButtonListener());
        viewJob.addActionListener(new ButtonListener());
        viewDevices.addActionListener(new ButtonListener());
        viewCust.addActionListener(new ButtonListener());
        listPayments.addActionListener(new ButtonListener());
        
        addJob.setPreferredSize(new Dimension(240,130));
        viewJob.setPreferredSize(new Dimension(240,130));
        viewDevices.setPreferredSize(new Dimension(240,130));
        viewCust.setPreferredSize(new Dimension(240,130));
        listPayments.setPreferredSize(new Dimension(240,130));
        
        optionsPanel.add(addJob);
        optionsPanel.add(viewJob);
        optionsPanel.add(viewDevices);
        optionsPanel.add(viewCust);
        optionsPanel.add(listPayments);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.GRAY);

        mainPanel.add(optionsPanel);
        mainPanel.add(detailsPanel);

        UI.getContentPane().add(mainPanel);
        
        UI.pack();
    }

    private JPanel startAddingJob(){
        JPanel devPanel, subStatPanel, JobPanel;
        JLabel cusNameL, cusNumberL, cusAddressL;
        JLabel devTypeL, devBrandL, devSerialL;
        JLabel devIssueL, devDateL, devDiagnosisL, devNotesL;

        JRadioButton statPend, statInProg, statAwait, statComplete;

        JTextField cusNameTF, custNumTF, cusAddrTF;
        JTextField devTypeTF, devBrandTF, devSerialTF;
        JTextField devIssTF, devDiagTF, devNotesTF;

        cusNameL = new JLabel("CUSTOMER NAME:");
        cusNumberL = new JLabel("CUSTOMER PHONE NUMBER:");
        cusAddressL = new JLabel("CUSTOMER ADDRESS:");
        devTypeL = new JLabel("DEVICE TYPE:");
        devBrandL = new JLabel("DEVICE BRAND:");
        devSerialL = new JLabel("DEVICE SERIAL NUMBER:");
        devIssueL = new JLabel("DEVICE ISSUE:");
        devDateL = new JLabel("DEVICE Date:");
        devDiagnosisL = new JLabel("TECHNICIAN'S DIAGNOSIS:");
        devNotesL = new JLabel("TECHNICIA'S NOTES:");

        statPend = new JRadioButton("PENDING");
        statAwait = new JRadioButton("AWAITING PARTS");
        statInProg = new JRadioButton("IN PROGRESS");
        statComplete = new JRadioButton("COMPLETE");
        subStatPanel = new JPanel(new GridLayout(4,1));

        subStatPanel.add(statPend);
        subStatPanel.add(statAwait);
        subStatPanel.add(statInProg);
        subStatPanel.add(statComplete);

        cusNameTF = new JTextField();
        //cusNameTF.setPreferredSize(new Dimension(100,20));
        custNumTF = new JTextField();
        cusAddrTF = new JTextField();
        devTypeTF = new JTextField(); 
        devBrandTF = new JTextField();
        devSerialTF = new JTextField();
        devIssTF = new JTextField();
        devDiagTF = new JTextField(); 
        devNotesTF = new JTextField();

        devPanel = new JPanel(new GridLayout(10,2));
        devPanel.setPreferredSize(new Dimension(690,700));

        devPanel.add(cusNameL);
        devPanel.add(cusNameTF);

        devPanel.add(cusAddressL);
        devPanel.add(cusAddrTF);

        devPanel.add(cusNumberL);
        devPanel.add(custNumTF);

        devPanel.add(devTypeL);
        devPanel.add(devTypeTF);

        devPanel.add(devBrandL);
        devPanel.add(devBrandTF);

        devPanel.add(devSerialL);
        devPanel.add(devSerialTF);

        devPanel.add(devIssueL);
        devPanel.add(devIssTF);

        devPanel.add(devDiagnosisL);
        devPanel.add(devDiagTF);

        devPanel.add(devNotesL);
        devPanel.add(devNotesTF);

        devPanel.add(subStatPanel);

        submitData = new JButton("SUBMIT");
        submitData.addActionListener(new ButtonListener());

        devPanel.add(submitData);
        
        JobPanel = new JPanel();
        JobPanel.add(devPanel);
        JobPanel.setVisible(true);
        return JobPanel;
    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed (ActionEvent event){
            if(event.getSource()==addJob){
                JPanel jobAddPanel = startAddingJob();
                detailsPanel.add(jobAddPanel);
                detailsPanel.updateUI();
                mainPanel.updateUI();
            }
            else if(event.getSource()==viewJob){

            }
            else if(event.getSource()==viewCust){

            }
            else if(event.getSource()==viewDevices){

            }
            else if(event.getSource()==listPayments){
                
            }
            else if (event.getSource()==submitData){
                
            }
        }
    }
}
