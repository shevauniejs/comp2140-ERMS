public class Job{
    static int idCounter = 000000;
    private int jobId;
    private Device jobDevice;
    private Customer jobCustomer;
    private double jobCost, amntPaid;
    private String techIssue, techDiagnosis, techNotes;


    public Job(){}

    public Job(Customer _customer, Device _device, double _jobCost, double _amtPaid, String _techIssue, String _diagnosis, String _techNotes){
        this.jobCustomer = _customer;
        this.jobDevice = _device;
        this.jobCost =_jobCost;
        this.amntPaid = _amtPaid;
        this.techIssue = _techIssue;
        this.techDiagnosis = _diagnosis;
        this.techNotes = _techNotes;
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
        return calcId();
    }
    public String getNotes(){
        return this.techNotes;
    }

    public String getDiagnosis(){
        return this.techDiagnosis;
    }

    public Device getDevice(){
        return this.jobDevice;
    }

    public String toString(){
        String str = "===========================BWOYZE+++ELECTRONICS=======================\n"+
                     "\n===========================96c MOLYNES ROAD (876-366-9211)=========================\n"+
                     "\n\n"+jobCustomer+
                     "\n\nDEVICE ISSUE: "+techIssue+
                     "\n\nCost: $"+jobCost+"\nAMOUNT PAID: $"+amntPaid+"\nBALANCE: $"+(jobCost-amntPaid)+
                     "\n\nDevices are held for a maximum of 30 days after technician notifies/calls you.\n"+
                     "Failure to retrieve after 30 days will incur a storage fee or device being sold."+
                     "\n\nThanks for doing business!";
        return str;
    }
}