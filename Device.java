import java.util.Date;

public class Device{
    protected int countID = 000000000;
    private int devID;
    private String  model, brand, type;
    Date dateRcvd;   
    
    public Device(){}

    public Device(String _model, String _brand, String _type){
        countID++;
        this.model = _model;
        this.brand = _brand;
        this.type = _type;
        dateRcvd = new Date();
    }

    public int getDevID(){
        devID = countID;
        return devID;
    }
}