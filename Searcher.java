import java.util.ArrayList;

public class Searcher {
    //Layer 3
    public static ArrayList<Job> devSearcher(ArrayList<Job> jobList, String searchParam){ //Search for job device w/ status
        ArrayList<Job> found = new ArrayList<Job>();
        for(Job jobInstance: jobList){
            if(jobInstance.getDevice().getStatus().contains(searchParam.toUpperCase())||
                jobInstance.getStoragePlace().contains(searchParam.toLowerCase())||
                (jobInstance.getStoragePlace().compareToIgnoreCase(searchParam)==0)||
                jobInstance.getStoragePlace().toLowerCase().contains(searchParam.toLowerCase())){
                found.add(jobInstance);
            }
        }
        return found;
    }

    public static int getCusViaId(int id){
        int idFound=0, x;
        for(x=0; x<Loader.getJobs().size();x++){
            if(Loader.getJobs().get(x).getCustomer().getCusId()==id){
                idFound = x;
                break;
            }else{
                idFound=-1;
            }
        }
        return idFound;
    }

    public static int getDevViaId(int id){
        int idFound=0, x;
        for(x=0; x<Loader.getJobs().size();x++){
            if(Loader.getJobs().get(x).getDevice().getDevID()==id){
                idFound = x;
                break;
            }else{
                idFound=-1;
            }
        }
        return idFound;
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
            if(jobInstance.getDevice().getStatus().contains(searchParam.toUpperCase())){
                found.add(jobInstance);
            }
        }
        return found;
    }
}
