import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class JobDetail extends JPanel{
        private JPanel devPanel = this;
        private JPanel subStatPanel;
        private JLabel cusNameL, cusNumberL, cusEmailL;
        private JLabel devTypeL, devBrandL, devSerialL;
        private JLabel devIssueL, devDiagnosisL, devNotesL, jobLocL;

        private ButtonGroup radios;
        private JRadioButton statPend, statInProg, statAwait, statComplete;

        private JTextField cusNameTF, custNumTF, cusEmailTF;
        private JTextField devTypeTF, devBrandTF, devSerialTF;
        private JTextField devIssTF, devDiagTF, devNotesTF, jobLocTF;

        private JButton submitData;

    public JobDetail(){
        cusNameL = new JLabel("CUSTOMER NAME:");
        cusNumberL = new JLabel("CUSTOMER PHONE NUMBER:");
        cusEmailL = new JLabel("CUSTOMER EMAIL:");
        devTypeL = new JLabel("DEVICE TYPE:");
        devBrandL = new JLabel("DEVICE BRAND/MODEL:");
        devSerialL = new JLabel("DEVICE SERIAL NUMBER:");
        devIssueL = new JLabel("DEVICE ISSUE:");
        devDiagnosisL = new JLabel("TECHNICIAN'S DIAGNOSIS:");
        devNotesL = new JLabel("TECHNICIAN'S NOTES:");

        jobLocL = new JLabel("LOCATION STORED");

        statPend = new JRadioButton("PENDING");
        statAwait = new JRadioButton("AWAITING PARTS");
        statInProg = new JRadioButton("IN PROGRESS");
        statComplete = new JRadioButton("COMPLETE");
        radios = new ButtonGroup();
        radios.add(statPend);
        radios.add(statInProg);
        radios.add(statAwait);
        radios.add(statComplete);
        
        subStatPanel = new JPanel(new GridLayout(4,1));

        subStatPanel.add(statPend);
        subStatPanel.add(statAwait);
        subStatPanel.add(statInProg);
        subStatPanel.add(statComplete);

        cusNameTF = new JTextField();
        custNumTF = new JTextField();
        cusEmailTF = new JTextField();
        devTypeTF = new JTextField(); 
        devBrandTF = new JTextField();
        devSerialTF = new JTextField();
        devIssTF = new JTextField();
        devDiagTF = new JTextField(); 
        devNotesTF = new JTextField();
        jobLocTF = new JTextField();

        devPanel.setLayout(new GridLayout(12,1));
        devPanel.setPreferredSize(new Dimension(400,700));
        devPanel.setBorder(BorderFactory.createLineBorder(Color.PINK));

        devPanel.add(cusNameL);
        devPanel.add(cusNameTF);

        devPanel.add(cusEmailL);
        devPanel.add(cusEmailTF);

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

        devPanel.add(jobLocL);
        devPanel.add(jobLocTF);

        devPanel.add(subStatPanel);

        submitData = new JButton("SUBMIT");
        submitData.addActionListener(new detailButtonListener());

        devPanel.add(submitData);
    }

    private class detailButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
                if(event.getSource()==submitData){
                    //Create Device and add to Data device list
                    String devStat ="";

                    java.util.Enumeration<AbstractButton> buttonEnum = radios.getElements();
                    while (buttonEnum.hasMoreElements()) {
                        AbstractButton button = buttonEnum.nextElement();                        
                        if (button.isSelected()) {
                            devStat = button.getText(); // Get the text directly from the JRadioButton/AbstractButton
                            break; 
                        }
                    }
                    Device currDevice = new Device(devSerialTF.getText(), devBrandTF.getText(), devTypeTF.getText(), devStat);
                    Driver.getData().getDevices().add(currDevice);
                    //Create Cust and add to Data cust list
                    Customer currCustomer = new Customer(cusNameTF.getText(), custNumTF.getText(), cusEmailTF.getText(), currDevice);
                    Driver.getData().getCustomers().add(currCustomer);
                    //Create Job and add to date Job list
                    Job currJob = new Job(currCustomer,currDevice,0.0, 0.0, devIssTF.getText(), devDiagTF.getText(), devNotesTF.getText(), jobLocTF.getText());
                    submitData.setBackground(Color.green);
                    PaymentProc pay = new PaymentProc(currJob);
                    pay.setVisible(true);
                    /*try {
                        Thread.sleep(1000, 0);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    devPanel.setVisible(false);*/
                }
        }
    }
    
}
