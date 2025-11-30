import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
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

    //protected JLabel statLabel;


    public SystemUI(){
        UI.setTitle("Electronic Repair MS");
        ButtonListener coreListener = new ButtonListener();
        UI.setDefaultCloseOperation(EXIT_ON_CLOSE);
        UI.setPreferredSize(new Dimension(1600,900)); //frame size
        //statLabel = new JLabel("STATUS");

        optionsPanel = new JPanel(new GridLayout(4,1)); //left side has options to add job etc
        detailsPanel = new JPanel(); //right side shows details and fields

        optionsPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        detailsPanel.setBorder(BorderFactory.createLineBorder(Color.blue));

        optionsPanel.setPreferredSize(new Dimension(360,800));
        detailsPanel.setPreferredSize(new Dimension(1200,800));

        addJob = new JButton("ADD JOB");
        viewJob = new JButton("VIEW/UPDATE JOB & GENERATE REPORT");
        viewDevices = new JButton("TRACK DEVICES");
        viewCust = new JButton("VIEW CUSTOMERS");


        addJob.addActionListener(coreListener);
        viewJob.addActionListener(coreListener);
        viewDevices.addActionListener(coreListener);
        viewCust.addActionListener(coreListener);
        
        addJob.setPreferredSize(new Dimension(240,130));
        viewJob.setPreferredSize(new Dimension(240,130));
        viewDevices.setPreferredSize(new Dimension(240,130));
        viewCust.setPreferredSize(new Dimension(240,130));
        
        optionsPanel.add(addJob);
        optionsPanel.add(viewJob);
        optionsPanel.add(viewDevices);
        optionsPanel.add(viewCust);

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
            if((event.getSource()==addJob)){
                detailsPanel.removeAll();
                JobDetail jobAddPanel = new JobDetail();
                detailsPanel.add(jobAddPanel);
                detailsPanel.updateUI();
                mainPanel.updateUI();
            }
            if(event.getSource()==viewJob){
                detailsPanel.removeAll();
                JobTracker jobTr = new JobTracker();
                detailsPanel.add(jobTr);
                detailsPanel.updateUI();

            }
            if(event.getSource()==viewCust){
                detailsPanel.removeAll();
                CustomerTracker cusTr = new CustomerTracker();
                detailsPanel.add(cusTr);
                detailsPanel.updateUI();

            }
            if(event.getSource()==viewDevices){
                detailsPanel.removeAll();
                DeviceTracker devTracker = new DeviceTracker();
                detailsPanel.add(devTracker);
                detailsPanel.updateUI();
            }
        }
    }
}
