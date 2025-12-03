import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {
    //Layer 3
    private static FileWriter DataWriter;
    private static File file;

    public static void writeTo(){
        try {
            file = new File("Jobs.dat");
                } catch (Exception e) {
                e.printStackTrace();
            }
        try {
            ArrayList <Job> tempJobs = Loader.getJobs();
            file.delete();
            DataWriter  = new FileWriter("Jobs.dat", true);
            for(Job job: tempJobs){
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
