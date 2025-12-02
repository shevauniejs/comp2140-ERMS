import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ERMSDBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/ERMS_DB";
    private static final String USER = "your_username"; // <-- CHANGE THIS
    private static final String PASSWORD = "your_password"; // <-- CHANGE THIS

    private Connection getConnection() throws SQLException {
        // DriverManager.getConnection handles driver registration automatically in modern Java
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void getAllCustomers() {
        String sql = "SELECT CustomerID, Name, Email FROM Customers";

        // try-with-resources automatically closes Connection, Statement, and ResultSet
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("--- Customers List ---");
            while (rs.next()) {
                int id = rs.getInt("CustomerID");
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                System.out.printf("ID: %d, Name: %s, Email: %s%n", id, name, email);
            }
            System.out.println("----------------------");

        } catch (SQLException e) {
            System.err.println("Error executing SELECT query:");
            e.printStackTrace();
        }
    }
    
    //ADD JOB
    public void addNewJob(int customerId, int deviceId, double cost, String issues) {
        String sql = "INSERT INTO Jobs (CustomerID, DeviceID, Cost, AmountPaid, TechIssues, TechDiagnosis, TechNotes, StoragePlace) " +
                     "VALUES (?, ?, ?, 0.00, ?, NULL, NULL, 'Pending')";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Set the parameters for the query
            pstmt.setInt(1, customerId);
            pstmt.setInt(2, deviceId);
            pstmt.setDouble(3, cost);
            pstmt.setString(4, issues);

            int rowsAffected = pstmt.executeUpdate();
            
            System.out.printf("%nNew job added successfully. Rows affected: %d%n", rowsAffected);

        } catch (SQLException e) {
            System.err.println("Error executing INSERT statement:");
            e.printStackTrace();
        }
    }


        
        
}
