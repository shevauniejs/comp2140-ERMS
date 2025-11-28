import java.util.Date;

public class Device{
    static int countID = 000000000;
    private int devID;
    private String  serial, brand, type;
    Date dateRcvd;   
    
    public Device(){}

    public Device(String _serial, String _brand, String _type){
        countID++;
        this.serial = _serial;
        this.brand = _brand;
        this.type = _type;
        dateRcvd = new Date();
    }

    public int getDevID(){
        devID = countID;
        return devID;
    }

    public String toString(){
        String str = "\n\tTYPE: "+type+"\tSERIAL #:"+serial+"\n\tBRAND: "+brand+"\tDATE: "+dateRcvd.toString();
        return str;
    }
}