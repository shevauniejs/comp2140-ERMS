import java.io.FileWriter;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Device{
    static int countId = 20000000;
    private int devId;
    private String  serial, brand, type, status;
    Date dateRcvd;
    FileWriter devDataWriter;   
    
    public Device(int _devId, String _type, String _serial, String _brand, String _status, Date _date){
        this.devId = _devId;
        this.type = _type;
        this.serial = _serial;
        this.brand = _brand;
        this.status= _status;
        this.dateRcvd = _date;
    }

    public Device(String _type, String _serial, String _brand,  String _status){
        devId = calcId();
        this.type = _type;
        this.serial = _serial;
        this.brand = _brand;
        this.status= _status;
        dateRcvd = new Date();
    }
    
    private int calcId(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int rand = random.nextInt(0+1, 10000);
        return countId+ rand;
    }

    public int getDevID(){
        return devId;
    }

    public void setDevId(int newID){
        devId = newID;
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
    
    public void setDate(Date newDate){
        dateRcvd = newDate;
    }

    public void setStatus(String newStatus){
        status = newStatus;
    }

    public String getStatus(){
        return this.status;
    }
    
    public String toString(){
        String str = devId+"="+type+"="+serial+"="+brand+"="+status+"="+dateRcvd.toString();
        return str;
    }
}