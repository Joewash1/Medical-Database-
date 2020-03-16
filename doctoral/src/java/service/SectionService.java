package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Grant;
import model.Section;
import model.StudentInfo;
import model.Supervisor;
import utils.DBUtils;

/**
 *
 */
public class SectionService {
    
    /**
     * get all sections
     * @return  sections
     */
    public static List<Section> getSections() {
        
        List<Section> sections = new ArrayList<>();
        
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
                    .prepareStatement("SELECT section.SectionId, course.CourseId, course.CName" +
                            " FROM section, course " +
                            " WHERE section.CourseId = course.CourseId;");
            
            // execute query
            rs = preparedStatement.executeQuery();
            
            //iterate and create the StudentInfo objects
            while (rs.next()) {
                
                sections.add(new Section(rs.getString(1) , rs.getString(2), rs.getString(3)));

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            DBUtils.closeResource(conn);
            DBUtils.closeResource(preparedStatement);
            DBUtils.closeResource(rs);
        } 
        
        return sections;        
    }
    
}
