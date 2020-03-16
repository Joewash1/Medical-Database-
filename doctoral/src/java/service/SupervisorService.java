package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.StudentInfo;
import model.Supervisor;
import utils.DBUtils;

/**
 *
 */
public class SupervisorService {
    
    /**
     * get all supervisor
     * @return  supervisors
     */
    public static List<Supervisor> getSupervisors() {
        
        List<Supervisor> supervisors = new ArrayList<>();
        
         // declare resultSet
        ResultSet rs = null;
        
        // declare prepared statement
        PreparedStatement preparedStatement = null;
        
        // declare connection
        Connection conn = null;
        try {
            // get connection
            conn = DBUtils.getConnection();

            // set prepared statement
            preparedStatement = conn
                    .prepareStatement("SELECT TT.InstructorId, instructor.FName, instructor.LName" +
                        " FROM TT, instructor" +
                        " WHERE TT.InstructorId = instructor.InstructorId");
            
            // execute query
            rs = preparedStatement.executeQuery();
            
            //iterate and create the StudentInfo objects
            while (rs.next()) {
                
                supervisors.add(new Supervisor(rs.getString(1) , rs.getString(2), rs.getString(3)));

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            DBUtils.closeResource(conn);
            DBUtils.closeResource(preparedStatement);
            DBUtils.closeResource(rs);
        } 
        
        return supervisors;        
    }
}
