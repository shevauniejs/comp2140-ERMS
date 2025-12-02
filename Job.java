import java.util.concurrent.ThreadLocalRandom;

public class Job{
    static int idCounter = 30000000;
    private int jobId;
    private Device jobDevice;
    private Customer jobCustomer;
    private double jobCost, amntPaid;
    private String techIssue, techDiagnosis, techNotes, storagePlace;



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

    public double getPaid(){
        return this.amntPaid;
    }

    public double getCost(){
        return this.jobCost;
    }


    public void setPaid(double p){
        this.amntPaid = p;
    }

    public void setCost(double c){
        this.jobCost = c;
    }

    private int calcId(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int rand = random.nextInt(0+1, 10000);
        return idCounter+ rand;
    }
    public int getJobId() {
        return jobId;
    }
    public String getNotes(){
        return this.techNotes;
    }

    public void setNotes(String newNote){
        this.techNotes = newNote;
    }

    public String getIssue(){
        return this.techIssue;
    }

    public void setIssue(String newIssue){
        this.techIssue = newIssue;
    }
    public String getDiagnosis(){
        return this.techDiagnosis;
    }

    public void setDiagnosis(String newDiagnosis){
        techDiagnosis = newDiagnosis;
    }
    public Device getDevice(){
        return this.jobDevice;
    }

    public void setDevice(Device newDevice){
        this.jobDevice = newDevice;
    }

    public Customer getCustomer(){
        return this.jobCustomer;
    }

    public void setCustomer(Customer newCustomer){
        this.jobCustomer = newCustomer;
    }

    public void setStoragePlace(String place) {
        this.storagePlace = place;
    }

    public String getStoragePlace(){
        return this.storagePlace;
    }
    public String toString(){
        String str = jobId+"="+jobCustomer+"="+techIssue+"="+ techDiagnosis+"="+ techNotes+"="+storagePlace+"="+jobCost+"="+amntPaid+"="+(jobCost-amntPaid);
        return str;
    }
}