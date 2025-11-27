public class Job{
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
}