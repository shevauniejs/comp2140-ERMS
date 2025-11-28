import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Customer{
    static int idCounter= 10000000;
    private int customerId;
    private String name;
    private String number;
    private String email;
    private Device cusDevice;
    FileWriter cusDataWriter;

    public Customer(){

    }

    public Customer(String _name, String _number, String _email, Device _device){
        this.name = _name;
        this.number = _number;
        this.email = _email;
        this.cusDevice =_device;
        this.customerId = calcId();
        try {
            cusDataWriter  = new FileWriter("Customers.dat", true);
            cusDataWriter.write(this.toString()+"\n");
            cusDataWriter.close();
        } catch (IOException e) {
            //Auto-generated catch block
            System.out.println("Issue writing to file..");
            e.printStackTrace();
        }
    }

    public String getName(){
        return name;
    }

    public String getNumber(){
        return number;
    }

    public String getEmail(){
        return this.email;
    }

    private int calcId(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int rand = random.nextInt(0+1, 10000);
        return idCounter+ rand;
    }
    public int getCusId() {
        return customerId;
    }    
    public String toString(){
        String str = customerId+"="+name+"="+number+"="+email+"="+ cusDevice;
        return str;
    }

}