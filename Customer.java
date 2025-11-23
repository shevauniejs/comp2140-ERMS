public class Customer{
    static int customerID;
    private String name;
    private String number;
    private String email;
    private Device cusDevice;

    public Customer(){

    }

    public Customer(String _name, String _number, String _email, Device _device){
        this.name = _name;
        this.number = _number;
        this.email = _email;
        this.cusDevice =_device;
    }

    public String getName(){
        return name;
    }

    public String getNumber(){
        return number;
    }

}