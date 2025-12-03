import java.util.concurrent.ThreadLocalRandom;
/**
 * Stores a Job
 * @author Shevaunie Senior
 */
public class Job{
    static int idCounter = 30000000;
    private int jobId;
    private Device jobDevice;
    private Customer jobCustomer;
    private double jobCost, amntPaid;
    private String techIssue, techDiagnosis, techNotes, storagePlace;


    /**
     * Constructor for job read from DB
     * @param _jobId unique job id
     * @param _customer customer associated with job
     * @param _techIssue issue device has 
     * @param _diagnosis what the technician discovered or assumptions
     * @param _techNotes random details to be noted
     * @param _storagePlace where the device is in the shop
     * @param _jobCost cost of the job
     * @param _amtPaid amount the customer paid
     */
    public Job(int _jobId, Customer _customer, String _techIssue, String _diagnosis, String _techNotes, String _storagePlace,  double _jobCost, double _amtPaid){
        System.out.println("Layer 2");
        this.jobCustomer = _customer;
        this.jobDevice = jobCustomer.getDevice();
        this.jobCost =_jobCost;System.out.println("Layer 3");
        this.amntPaid = _amtPaid;
        this.techIssue = _techIssue;
        this.techDiagnosis = _diagnosis;
        this.techNotes = _techNotes;
        this.storagePlace = _storagePlace;
        this.jobId =_jobId;
    }

    /**
     * 
     * @param _customer cusomer associated with job
     * @param _techIssue issue that needs repair
     * @param _diagnosis hints or assumptions as to cause 
     * @param _techNotes additional details
     * @param _storagePlace location device is stashed
     * @param _jobCost amount the repair costs
     * @param _amtPaid aount the customer paid
     */
    public Job(Customer _customer, String _techIssue, String _diagnosis, String _techNotes, String _storagePlace, double _jobCost, double _amtPaid){
        System.out.println("Layer 2");
        this.jobCustomer = _customer;
        this.jobDevice = jobCustomer.getDevice();
        this.jobCost =_jobCost;
        this.amntPaid = _amtPaid;
        this.techIssue = _techIssue;
        this.techDiagnosis = _diagnosis;
        this.techNotes = _techNotes;
        this.storagePlace = _storagePlace;
        this.jobId =calcId();

    }

    /**
     * gets the amount paid by the customer
     * @return amount customer paid as double
     */
    public double getPaid(){
        return this.amntPaid;
    }

    /**
     * gets the cost of the repair
     * @return double representing repair cost
     */
    public double getCost(){
        return this.jobCost;
    }

    /**
     * sets the amount the customer paid
     * @param p amount paid from textfield
     */
    public void setPaid(double p){
        this.amntPaid = p;
    }

    /**
     * sets the cost of the job
     * @param c cost form input field
     */
    public void setCost(double c){
        this.jobCost = c;
    }


    /**
     * randomly generates unique ID for job
     * @return integer representing ID
     */
    private int calcId(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int rand = random.nextInt(0+1, 10000);
        return idCounter+ rand;
    }
    /**
     * gets the unique number associated with job
     * @return job id as an integer
     */
    public int getJobId() {
        return jobId;
    }

    /**
     * gets note associated with job
     * @return notes as string
     */
    public String getNotes(){
        return this.techNotes;
    }
    /**
     * assigns note to job
     * @param newNote new note
     */
    public void setNotes(String newNote){
        this.techNotes = newNote;
    }

    /**
     * gets issue with device
     * @return issue as string
     */
    public String getIssue(){
        return this.techIssue;
    }

    /**
     * adds device issue to job
     * @param newIssue issue with device
     */
    public void setIssue(String newIssue){
        this.techIssue = newIssue;
    }

    /**
     * get's hint or assumptions about repair solution
     * @return diagnosis as string
     */
    public String getDiagnosis(){
        return this.techDiagnosis;
    }

    /**
     * set assumptions or hints about repair solution
     * @param newDiagnosis new diagnosis found
     */
    public void setDiagnosis(String newDiagnosis){
        techDiagnosis = newDiagnosis;
    }
    /**
     * gets device associated with job
     * @return device
     */
    public Device getDevice(){
        return this.jobDevice;
    }

    /**
     * sets device associated with job
     * @param newDevice new device to be associated with job
     */
    public void setDevice(Device newDevice){
        this.jobDevice = newDevice;
    }

    /**
     * gets customer associated with job
     * @return customer
     */
    public Customer getCustomer(){
        return this.jobCustomer;
    }

    /**
     * assigns customer to job
     * @param newCustomer new customer information
     */
    public void setCustomer(Customer newCustomer){
        this.jobCustomer = newCustomer;
    }


    /**
     * sets where device is stored
     * @param place
     */
    public void setStoragePlace(String place) {
        this.storagePlace = place;
    }

    /**
     * gets where the device is stored
     * @return storage location as string 
     */
    public String getStoragePlace(){
        return this.storagePlace;
    }

    /**
     * Outputs job info as string
     */
    public String toString(){
        String str = jobId+"="+jobCustomer+"="+techIssue+"="+ techDiagnosis+"="+ techNotes+"="+storagePlace+"="+jobCost+"="+amntPaid+"="+(jobCost-amntPaid);
        return str;
    }
}