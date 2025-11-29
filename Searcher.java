import java.util.ArrayList;

public class Searcher {
    
    public static String devSearcher(ArrayList<Device> devList, String searchParam){
        String found = "";
        for(Device devInstance: devList){
            if(devInstance.getStatus()==searchParam){
                found = devInstance.toString();
            }
        }
        return found;
    }

    public static String cusSearcher(ArrayList<Customer> cusList, String searchParam){
        String found = "";
        for(Customer cusInstance: cusList){
            if(cusInstance.getName()==searchParam){
                found = cusInstance.toString();
            }else if(cusInstance.getNumber()==searchParam){
                found = cusInstance.toString();
            }else if(cusInstance.getCusId()==Integer.parseInt(searchParam)){
                found = cusInstance.toString();
            }
        }
        return found;
    }

    public static ArrayList<Job> jobSearcher(ArrayList<Job> jobList, String searchParam){
        ArrayList <Job> found = new ArrayList<Job>();
        for(Job jobInstance: jobList){
            if(jobInstance.getDevice().getStatus()==searchParam){
                found.add(jobInstance);
            }
        }
        return found;
    }
}
