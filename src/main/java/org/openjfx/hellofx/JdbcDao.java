
package org.openjfx.hellofx;
import org.openjfx.hellofx.Insurance;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class JdbcDao {

    // Replace below database url, username and password with your actual database credentials
    private static final String INSERT_QUERY = "INSERT INTO user_registration (full_name, email_id, password) VALUES (?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT id, email_id, insurance_type, insurance_name, monthly_premium, tenure FROM insurance_plan WHERE email_id=?";
    private static final String SEARCH_QUERY = "SELECT id FROM user_registration WHERE email_id = ? and password = ?";
    
    public void insertRecord(String fullName, String emailId, String password) throws SQLException {

        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
        try (
                
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood_project?useSSL=false&allowPublicKeyRetrieval=true","root","Amma@1806");
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY) ){
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, emailId);
            preparedStatement.setString(3, password);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }
    
    public List<Insurance> getRecord(String emailId) throws SQLException
    {
        List<Insurance> plans = new ArrayList<>();
        try(
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood_project?useSSL=false&allowPublicKeyRetrieval=true","root","Amma@1806");
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);) 
        {
            preparedStatement.setString(1,emailId);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
               Insurance insure = new Insurance();
               insure.setId(rs.getInt("id"));
               System.out.println(insure.getId());
               insure.setEmail_id(rs.getString("email_id"));
               System.out.println(insure.getEmail_id());
               insure.setInsurance_type(rs.getString("insurance_type"));
               System.out.println(insure.getInsurance_type());
               insure.setInsurance_name(rs.getString("insurance_name"));
               System.out.println(insure.getInsurance_name());
               insure.setMonthly_premium(rs.getInt("monthly_premium"));
               System.out.println(insure.getMonthly_premium());
               insure.setTenure(rs.getInt("tenure"));
               plans.add(insure);
            }
            
            return plans;
        }catch(SQLException e){
        
            printSQLException(e);     
        }
        return plans;
    }
    
    public boolean searchRecord(String emailId, String password) throws SQLException
    {
        try(
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood_project?useSSL=false&allowPublicKeyRetrieval=true","root","Amma@1806");
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_QUERY);) 
        {
            preparedStatement.setString(1,emailId);
            preparedStatement.setString(2,password);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rs);
            return rs.next();
        }catch(SQLException e){
        
            printSQLException(e);     
        }
        return false;
    }
    
    
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
