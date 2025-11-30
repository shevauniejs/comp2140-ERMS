import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {
    private ArrayList<Job> jobs;
    private static FileWriter DataWriter;
    
    public Writer(ArrayList<Job> jobList){
        this.jobs = jobList;
    }

    public void writeTo(){
        try {
            DataWriter  = new FileWriter("Jobs.dat");
            for(Job job: jobs){
                DataWriter = new FileWriter("Job.dat",true);
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
