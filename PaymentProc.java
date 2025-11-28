import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PaymentProc extends JFrame{
    static int idCounter = 40000000;
    private int transactionId;
    private JLabel lCost, lPaid, lBalance;
    private JTextField tfCost, tfPaid;
    private JButton printReceipt;
    private JPanel paymentPane;
    private JFrame window = this;
    private Job currJob;

    public PaymentProc(Job aJob){
        this.setPreferredSize(new Dimension(400,300));
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setTitle("Payment");

        this.transactionId = calcId();
        currJob = aJob;
        paymentPane = new JPanel(new GridLayout(4, 2));
        paymentPane.setSize(new Dimension(380,280));
        lCost = new JLabel("COST");
        lPaid = new JLabel("PAID");

        lBalance = new JLabel();

        tfCost = new JTextField();
        tfPaid = new JTextField();


        printReceipt = new JButton("PRINT RECEIPT");
        printReceipt.addActionListener(new ButtonListener());


        paymentPane.add(lCost);
        paymentPane.add(tfCost);
        paymentPane.add(lPaid);
        paymentPane.add(tfPaid);
        paymentPane.add(lBalance);
        paymentPane.add(printReceipt);

        window.add(paymentPane);
        window.pack();
    }

    private int calcId(){
        return idCounter++;
    }
    public int getTransId() {
        return transactionId;
    } 

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            if(event.getSource()==printReceipt){
                currJob.setCost(Double.parseDouble(tfCost.getText()));
                currJob.setPaid(Double.parseDouble(tfPaid.getText())); 
                Driver.getData().getJobs().add(currJob);
                double balance = currJob.getCost()-currJob.getPaid();
                System.out.println(balance);
                lBalance.setText("BALANCE: $"+Double.toString(balance));
                System.out.println(currJob);
                Receipt receipt = new Receipt("\t\t===========================BWOYZE+++ELECTRONICS=======================\n"+
                     "\n\t\t===========================96c MOLYNES ROAD (876-366-9211)=========================\n"+
                     "\n\n\tCUSTOMER NAME: "+currJob.getCustomer().getName()+"\tNUMBER: "+currJob.getCustomer().getNumber()+"\tEMAIL"+currJob.getCustomer().getEmail()+
                     "\n\n\tDEVICE:"+
                     "\n\tTYPE: "+currJob.getDevice().getType()+"\tSERIAL #:"+currJob.getDevice().getSerialNum()+
                     "\n\tBRAND/MODEL: "+currJob.getDevice().getBrand_ModelInfo()+"\tDATE: "+ currJob.getDevice().getDate()+
                     "\n\n\tDEVICE ISSUE: "+currJob.getIssue()+
                     "\n\n\tDEVICE LOCATION IN STORE: "+ currJob.getStoragePlace()+
                     "\n\n\tCost: $"+currJob.getCost()+"\n\tAMOUNT PAID: $"+currJob.getPaid()+"\n\tBALANCE: $"+(currJob.getCost()-currJob.getPaid())+
                     "\n\n\tTRANSACTION: "+transactionId+
                     "\n\n\tDevices are held for a maximum of 30 days after technician notifies/calls you.\n"+
                     "\tFailure to retrieve after 30 days will incur a storage fee or device being sold."+
                     "\n\n\t\t\tThanks for doing business!");
                    
                    FileWriter jobDataWriter;
                    try {
                        jobDataWriter  = new FileWriter("Jobs.dat");
                        jobDataWriter.write(currJob.toString());
                        jobDataWriter.close();
                    } catch (IOException e) {
                        //Auto-generated catch block
                        System.out.println("Issue writing to file..");
                        e.printStackTrace();
                    }                
                receipt.setVisible(true);      
            }
        }
    }
}
