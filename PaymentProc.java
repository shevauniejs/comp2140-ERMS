import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PaymentProc extends JFrame{
    private JLabel lCost, lPaid, lBalance;
    private JTextField tfCost, tfPaid;
    private JButton printReceipt;
    private JPanel paymentPane;
    private JFrame window = this;
    private Job currJob;

    public PaymentProc(Job aJob){
        this.setPreferredSize(new Dimension(400,300));
        currJob = aJob;
        paymentPane = new JPanel(new GridLayout(3, 2));
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
            }
        }
    }
}
