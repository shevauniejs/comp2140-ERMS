import java.awt.Dimension;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;


public class Receipt extends JFrame{
    private String details;
    private FileWriter receipt;
    private JTextArea recLabel;
    public Receipt(String _details){
        this.details = _details;
        recLabel = new JTextArea(details);
        setSize(new Dimension(600,500));  
        getContentPane().add(recLabel);  
        try{
        receipt = new FileWriter("receipt.txt");
        receipt.write(details);
        receipt.close();
        }catch(IOException e){
            System.out.println("Issue w/ file");
        }
    }
}
