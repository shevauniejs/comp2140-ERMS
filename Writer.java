import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {
    private static FileWriter DataWriter;

    public static void writeTo(){
        try {
            ArrayList <Job> tempJobs = Loader.getJobs();
            DataWriter  = new FileWriter("Jobs.dat");
            for(Job job: tempJobs){
                DataWriter = new FileWriter("Jobs.dat",true);
                DataWriter.write(job.toString()+"\n");
            }
            DataWriter.close();
        }catch (IOException e) {
            //Auto-generated catch block
            System.out.println("Issue writing to file..");
            e.printStackTrace();
                } 
    }
}
