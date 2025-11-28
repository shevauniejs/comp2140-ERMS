import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class SystemUI extends JFrame{
    private JFrame UI = this;
    private JPanel optionsPanel;
    private JPanel detailsPanel;
    private JPanel mainPanel;

    private JButton addJob;
    private JButton viewJob;
    private JButton viewDevices;
    private JButton viewCust;
    private JButton listPayments;
    private boolean detailsActive;

    //protected JLabel statLabel;


    public SystemUI(){
        detailsActive = false;
        UI.setDefaultCloseOperation(EXIT_ON_CLOSE);
        UI.setPreferredSize(new Dimension(1024,768)); //frame size
        //statLabel = new JLabel("STATUS");

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

        //mainPanel.add(statLabel);
        mainPanel.add(optionsPanel);
        mainPanel.add(detailsPanel);

        UI.getContentPane().add(mainPanel);
        
        UI.pack();
    }

    
    private class ButtonListener implements ActionListener{
        public void actionPerformed (ActionEvent event){
            if((event.getSource()==addJob) &&(detailsActive==false) ){
                detailsPanel.removeAll();
                JobDetail jobAddPanel = new JobDetail();
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
        }
    }
}
