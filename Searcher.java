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
            if((cusInstance.getName().contains(searchParam))||
            (cusInstance.getNumber().contains(searchParam))||
            (cusInstance.getEmail()==searchParam)
        ){
                found.add(cusInstance);
            }
        }
        return found;
    }

    public static ArrayList<Job> jobSearcher(ArrayList<Job> jobList, String searchParam){
        ArrayList <Job> found = new ArrayList<Job>();
        System.out.println("EXECUTED");
        for(Job jobInstance: jobList){
            System.out.println("SEARCHING");
            if(jobInstance.getDevice().getStatus().compareToIgnoreCase(searchParam)==0){
                found.add(jobInstance);
                System.out.println("JOB FOUND");
            }
        }
        return found;
    }
}
