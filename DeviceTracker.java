import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DeviceTracker extends JPanel{
    private ArrayList <Device> devices = Driver.getData().getDevices(); 
    private JTable devTable;
    private DefaultTableModel devTableModel;
}
