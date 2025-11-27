public class Driver {
    static DataClass techData;
    static SystemUI GUI;
    public static void main(String[] args) {
        techData = new DataClass(); 
        GUI = new SystemUI();
        GUI.setVisible(true);   
    }
}
