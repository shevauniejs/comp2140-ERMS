import java.util.ArrayList;

public class Searcher {
    
    public static ArrayList<Device> devSearcher(ArrayList<Device> devList, String searchParam){
        ArrayList<Device> found = new ArrayList<Device>();
        for(Device devInstance: devList){
            if(devInstance.getStatus().toLowerCase().contains(searchParam)|| (devInstance.getStatus().compareToIgnoreCase(searchParam)==0)){
                found.add(devInstance);
            }
        }
        return found;
    }

    public static Customer getCusViaId(ArrayList<Customer> cusList, int id){
        Customer cs = null;

        for(Customer c: cusList){
            if(c.getCusId()==id){
                cs = c;
                break;
            }
        }
        return cs;
    }

    public static Customer cusSearcher(ArrayList<Customer> cusList, String searchParam){
        Customer found = null;
        for(Customer cusInstance: cusList){
            if((cusInstance.getName()==searchParam)||
            (cusInstance.getNumber()==searchParam)||
            (cusInstance.getEmail()==searchParam)
        ){
                found = cusInstance;
            }
        }
        return found;
    }
    
    public static ArrayList<Customer> cusSearcher(ArrayList<Customer> cusList, Customer _null_, String searchParam){
        ArrayList<Customer> found = new ArrayList<Customer>();

        for(Customer cusInstance: cusList){
            if((cusInstance.getName().toLowerCase().contains(searchParam.toLowerCase()))||
            (cusInstance.getNumber().toLowerCase().contains(searchParam.toLowerCase()))||
            (cusInstance.getEmail().toLowerCase().contains(searchParam.toLowerCase()))
        ){
                found.add(cusInstance);
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
