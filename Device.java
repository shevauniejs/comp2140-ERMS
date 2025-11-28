import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Device{
    static int countId = 20000000;
    private int devId;
    private String  serial, brand, type, status;
    Date dateRcvd;
    FileWriter devDataWriter;   
    
    public Device(){}

    public Device(String _serial, String _brand, String _type, String _status){
        devId = calcId();
        this.serial = _serial;
        this.brand = _brand;
        this.type = _type;
        this.status= _status;
        dateRcvd = new Date();
        try {
            devDataWriter  = new FileWriter("Devices.dat", true);
            devDataWriter.write(this.toString()+"\n");
            devDataWriter.close();
        } catch (IOException e) {
            //Auto-generated catch block
            System.out.println("File issue");
            e.printStackTrace();
        }
    }
    
    private int calcId(){
        return countId++;
    }

    public int getDevID(){
        return devId;
    }

    public String getType(){
        return this.type;
    }

    public String getBrand_ModelInfo(){
        return this.brand;
    }

    public String getSerialNum(){
        return this.serial;
    }

    public String getDate(){
        return dateRcvd.toString();
    }

    public void setStatus(String newStatus){
        status = newStatus;
    }

    public String getStatus(){
        return this.status;
    }
    
    public String toString(){
        String str = devId+")+("+type+")+("+serial+")+("+brand+")+("+status+")+("+dateRcvd.toString();
        return str;
    }
}