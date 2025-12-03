import java.io.FileWriter;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;


/**Stores Device 
 * @author Shevaunie Senior
 */
public class Device{
    static int countId = 20000000;
    private int devId;
    private String  serial, brand, type, status;
    private LocalDate dateRcvd;
    FileWriter devDataWriter;   
    
    /**
     * Creates customer when read fromDB
     * @param _devId device ID
     * @param _type device type
     * @param _serial device serial number
     * @param _brand device brand/model/description
     * @param _status device status
     * @param _date device's date
     */
    public Device(int _devId, String _type, String _serial, String _brand, String _status, LocalDate _date){
        System.out.println("Layer 2");
        this.devId = _devId;
        this.type = _type;
        this.serial = _serial;
        this.brand = _brand;
        this.status= _status;
        this.dateRcvd = _date;
    }

    /**
     * Creates a device
     * @param _type device type
     * @param _serial device serial number
     * @param _brand device brand/model/description
     * @param _status device status
     */

    public Device(String _type, String _serial, String _brand,  String _status){
        System.out.println("Layer 2");
        devId = calcId();
        this.type = _type;
        this.serial = _serial;
        this.brand = _brand;
        this.status= _status;
        dateRcvd = LocalDate.now();
    }
    
    /**
     * randomly generates the ID
     * @return
     */
    private int calcId(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int rand = random.nextInt(0+1, 10000);
        return countId+ rand;
    }

    /**
     * gets device ID
     * @return returns ID as a string
     */

    public int getDevID(){
        return devId;
    }

    /**
     * updates device ID
     * @param newID new device ID
     */

    public void setDevId(int newID){
        devId = newID;
    }


    /**
     * gets the device's type
     * @return device's type as a string
     */
    public String getType(){
        return this.type;
    }

    /**
     * gets devices brand
     * @return device's brand, desciption etc as sting
     */
    public String getBrand_ModelInfo(){
        return this.brand;
    }

    /**
     *  gets the device's serial number
     * @return device's serial number as sting
     */
    public String getSerialNum(){
        return this.serial;
    }

    /** 
     * gets date the device was received
     * @return date the device was received as LocalDate
     */
    public LocalDate getDate(){
        return dateRcvd;
    }
    
    /**
     * sets new date
     * @param newDate new date a LocalDate
     */
    public void setDate(LocalDate newDate){
        dateRcvd = newDate;
    }

    /**
     * sets device status
     * @param newStatus  new status as string
     */
    public void setStatus(String newStatus){
        status = newStatus;
    }

    /**
     * gets device status
     * @return status of device as a string
     */
    public String getStatus(){
        return this.status;
    }
    
    /**
     * device as string
     */
    public String toString(){
        String str = devId+"="+type+"="+serial+"="+brand+"="+status+"="+dateRcvd.toString();
        return str;
    }
}