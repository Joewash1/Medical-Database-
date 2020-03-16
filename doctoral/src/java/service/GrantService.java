package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Grant;
import model.StudentInfo;
import model.Supervisor;
import utils.DBUtils;

/**
 *
 */
public class GrantService {
    
    /**
     * get all grants
     * @return  grants
     */
    public static List<Grant> getGrants() {
        
        List<Grant> grants = new ArrayList<>();
        
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
                    .prepareStatement("SELECT `grants`.AccountNo, grants.`Type`,`grants`.GrantTitle, "
                            + " grants.Sourc, grants.StDate, grants.EndDate, "
                            + " grants.StAmount, grants.CurrBalance" 
                            + " FROM grants;");
            
            // execute query
            rs = preparedStatement.executeQuery();
            
            //iterate and create the StudentInfo objects
            while (rs.next()) {
                
                grants.add(new Grant(rs.getString(1) , rs.getString(2), rs.getString(3),  rs.getString(4), 
                        utils.Utils.toDate(rs.getDate(5)), utils.Utils.toDate(rs.getDate(6)),                       
                        String.format("%.0f", rs.getFloat(7)), String.format("%.0f", rs.getFloat(8))));

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            DBUtils.closeResource(conn);
            DBUtils.closeResource(preparedStatement);
            DBUtils.closeResource(rs);
        } 
        
        return grants;        
    }
    
}
