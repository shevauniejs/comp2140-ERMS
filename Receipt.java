import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JTextArea;


public class Receipt extends JFrame{
    private String details;
    //private FileWriter receipt;
    private JTextArea recLabel;
    public Receipt(int _transID, String _details){
        this.setTitle("Receipt Preview");
        this.details = _details;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        recLabel = new JTextArea(details);
        setSize(new Dimension(800,500));  
        getContentPane().add(recLabel);  

        String userhome = System.getProperty("user.home");
        Path documentsPath = Paths.get(userhome, "Documents");
        Path filePath = documentsPath.resolve("receipt"+_transID+".txt");

        try {
            // 4. Write the content to the file.
            // This method creates the file if it doesn't exist and overwrites it if it does.
            Files.writeString(filePath, details);
            
            System.out.println("✅ File exported successfully!");
        } catch (IOException e) {
            System.err.println("❌ Failed to write the file:");
            e.printStackTrace();
        }
    }
}
