import java.io.FileWriter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Creates customer
 * @Author Shevaunie Senior
 * @Version 1.0
 */
public class Customer{
    static int idCounter= 10000000;
    private int customerId;
    private String name;
    private String number;
    private String email;
    private Device cusDevice;
    private ArrayList<Job> cusJobs;
    FileWriter cusDataWriter;

    /**Constructor for when device is read from DB
     * 
     * @param _cusId unique customer id read in
     * @param _name customer's name
     * @param _number customer's number
     * @param _email customer's email
     * @param _device customer's device
     */
    public Customer(int _cusId, String _name, String _number, String _email, Device _device){
        System.out.println("Layer 2");
        this.name = _name;
        this.number = _number;
        this.email = _email;
        this.cusDevice =_device;
        this.customerId = _cusId;
    }
    /** Constructor - Basic
     * 
     * @param _name customer's name
     * @param _number customer's number
     * @param _email customer's email
     * @param _device customer's device
     */

    public Customer(String _name, String _number, String _email, Device _device){
        System.out.println("Layer 2");
        this.name = _name;
        this.number = _number;
        this.email = _email;
        this.cusDevice =_device;
        this.customerId = calcId();
        this.cusJobs = new ArrayList<Job>();
    }
    /** function to add job to customer
     * 
     * @param cJob
     */
    public void addJob(Job cJob){
        cusJobs.add(cJob);
    }
    /**
     * function to get jobs associated with customer
     * @return a list of jobs
     */
    public ArrayList<Job> getCusJobs(){
        return this.cusJobs;
    }
    /**
     * gets device associated w/ customer
     * @return customer's device
     */
    public Device getDevice(){
        return this.cusDevice;
    }

    /**
     * gets customer's name
     * @return customer's name as string
     */
    public String getName(){
        return name;
    }

    /**
     * get customer's number
     * @return customer's number
     */
    public String getNumber(){
        return number;
    }

    /**
     * gets customer's email
     * @return customer's email as string
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * sets customers new name
     * @param newName new name for customer
     */
    public void setName(String newName){
        this.name=newName;
    }


    /**
     * sets new number for customer
     * @param newNumber new number for customer
     */

    public void setNumber(String newNumber){
        this.number=newNumber;
    }

    /**
     * sets new email for customer
     * @param newEmail new email associated w/ customer
     */
    public void setEmail(String newEmail){
        this.email = newEmail;
    }

    /**
     * randomly generates ID
     * @return radomnly generated ID as in
     */
    private int calcId(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int rand = random.nextInt(0+1, 10000);
        return idCounter+ rand;
    }

    /**
     * get's unique ID generated
     * @return customer's ID
     */
    public int getCusId() {
        return customerId;
    } 
    
    /**
     * Customer info as string
     */
    public String toString(){
        String str = customerId+"="+name+"="+number+"="+email+"="+ cusDevice;
        return str;
    }

}