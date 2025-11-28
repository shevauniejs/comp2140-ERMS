import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Device{
    static int countId = 20000000;
    private int devId;
    private String  serial, brand, type;
    Date dateRcvd;
    FileWriter devDataWriter;   
    
    public Device(){}

    public Device(String _serial, String _brand, String _type){
        devId = calcId();
        this.serial = _serial;
        this.brand = _brand;
        this.type = _type;
        dateRcvd = new Date();
        try {
            devDataWriter  = new FileWriter("Devices.dat");
            devDataWriter.write(this.toString());
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
    public String toString(){
        String str = devId+" "+type+" "+serial+" "+brand+" "+dateRcvd.toString();
        return str;
    }
}