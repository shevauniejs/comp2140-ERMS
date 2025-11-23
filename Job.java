public class Job{
    private Device jobDevice;
    private Customer jobCustomer;
    private double jobCost, amntPaid;
    private String technician;

    public Job(){}

    public Job(Customer _customer, Device _device, double _jobCost, double _amtPaid, String _technician){
        this.jobCustomer = _customer;
        this.jobDevice = _device;
        this.jobCost =_jobCost;
        this.amntPaid = _amtPaid;
        this.technician = _technician;
    }
}