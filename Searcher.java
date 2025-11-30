import java.util.ArrayList;

public class Searcher {
    
    public static ArrayList<Job> devSearcher(ArrayList<Job> jobList, String searchParam){
        ArrayList<Job> found = new ArrayList<Job>();
        for(Job jobInstance: jobList){
            if(jobInstance.getDevice().getStatus().toLowerCase().contains(searchParam)|| (jobInstance.getDevice().getStatus().compareToIgnoreCase(searchParam)==0)){
                found.add(jobInstance);
            }
        }
        return found;
    }

    public static Customer getCusViaId(int id){
        Customer cs = null;

        for(Job j: Loader.getJobs()){
            if(j.getCustomer().getCusId()==id){
                cs = j.getCustomer();
                break;
            }
        }
        return cs;
    }

    public static Job cusSearcher(ArrayList<Job> jobList, String searchParam){
        Job found = null;
        for(Job jobInstance: jobList){
            if((jobInstance.getCustomer().getName()==searchParam)||
            (jobInstance.getCustomer().getNumber()==searchParam)||
            (jobInstance.getCustomer().getEmail()==searchParam)
        ){
                found = jobInstance;
            }
        }
        return found;
    }
    
    public static ArrayList<Job> cusSearcher(ArrayList<Job> jobList, Customer _null_, String searchParam){
        ArrayList<Job> found = new ArrayList<Job>();

        for(Job jobInstance: jobList){
            if((jobInstance.getCustomer().getName().toLowerCase().contains(searchParam.toLowerCase()))||
            (jobInstance.getCustomer().getNumber().toLowerCase().contains(searchParam.toLowerCase()))||
            (jobInstance.getCustomer().getEmail().toLowerCase().contains(searchParam.toLowerCase()))
        ){
                found.add(jobInstance);
            }
        }
        return found;
    }

    public static ArrayList<Job> jobSearcher(ArrayList<Job> jobList, String searchParam){
        ArrayList <Job> found = new ArrayList<Job>();
        for(Job jobInstance: jobList){
            if(jobInstance.getDevice().getStatus().toLowerCase().contains(searchParam)){
                found.add(jobInstance);
            }
        }
        return found;
    }
}
