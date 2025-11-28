public class Job{
    static int idCounter = 000000;
    private int jobId;
    private Device jobDevice;
    private Customer jobCustomer;
    private double jobCost, amntPaid;
    private String techIssue, techDiagnosis, techNotes, storagePlace;


    public Job(){}

    public Job(Customer _customer, Device _device, double _jobCost, double _amtPaid, String _techIssue, String _diagnosis, String _techNotes, String _storagePlace){
        this.jobCustomer = _customer;
        this.jobDevice = _device;
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
        return idCounter++;
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

    public String getDiagnosis(){
        return this.techDiagnosis;
    }

    public void setDiagnosis(String newDiagnosis){
        techDiagnosis = newDiagnosis;
    }
    public Device getDevice(){
        return this.jobDevice;
    }

    public void setStoragePlace(String place) {
        this.storagePlace = place;
    }

    public String getStoragePlace(){
        return this.storagePlace;
    }
    public String toString(){
        String str = "\t\t===========================BWOYZE+++ELECTRONICS=======================\n"+
                     "\n\t\t===========================96c MOLYNES ROAD (876-366-9211)=========================\n"+
                     "\n\n\t"+jobCustomer+
                     "\n\n\tDEVICE ISSUE: "+techIssue+
                     "\n\n\tDEVICE LOCATION IN STORE: "+ storagePlace+
                     "\n\n\tCost: $"+jobCost+"\n\tAMOUNT PAID: $"+amntPaid+"\n\tBALANCE: $"+(jobCost-amntPaid)+
                     "\n\n\tDevices are held for a maximum of 30 days after technician notifies/calls you.\n"+
                     "\tFailure to retrieve after 30 days will incur a storage fee or device being sold."+
                     "\n\n\t\t\tThanks for doing business!";
        return str;
    }
}