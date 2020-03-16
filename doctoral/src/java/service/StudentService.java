package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.StudentInfo;
import utils.DBUtils;

/**
 *
 */
public class StudentService {
    
    /**
     * search student by first name, last name
     * 
     * @param firtname
     * @param lastname
     * @return  student with supervisor, type, milestones...
     */
    public static List<StudentInfo> searchStudentInfo(String firtname, String lastname) {
        
        List<StudentInfo> students = new ArrayList<>();
        
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
                    .prepareStatement("SELECT instructor.FName, instructor.LName," +
                        " IF(EXISTS ( SELECT * FROM gra WHERE gra.StudentId = phdstudent.StudentId) , 'GRA', " +
                        " IF(EXISTS ( SELECT * FROM gta WHERE gta.StudentId = phdstudent.StudentId) , 'GTA', " +
                        " IF(EXISTS ( SELECT * FROM scholarshipsupport WHERE scholarshipsupport.StudentId = phdstudent.StudentId) , 'scholarship', " +
                        " IF(EXISTS ( SELECT * FROM selfsupport WHERE selfsupport.StudentId = phdstudent.StudentId) , 'self', ''))" +
                        " )) AS StudentType, " +
                        " milestone.MName, milestonespassed.PassDate" +
                        " FROM phdstudent left join instructor on instructor.InstructorId = phdstudent.Supervisor"
                            + " left join milestonespassed on phdstudent.StudentId = milestonespassed.StudentId"
                            + " left join milestone on milestonespassed.MID = milestone.MID" +
                        " WHERE phdstudent.FName = ? AND phdstudent.LName = ?");
            
            // execute query
            preparedStatement.setString(1, firtname);
            preparedStatement.setString(2, lastname);      

            rs = preparedStatement.executeQuery();
            
            //iterate and create the StudentInfo objects
            while (rs.next()) {
                
                students.add(new StudentInfo(rs.getString(1)+ " " + rs.getString(2), rs.getString(3), rs.getString(4), 
                        utils.Utils.toDate(rs.getDate(5))));

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            DBUtils.closeResource(conn);
            DBUtils.closeResource(preparedStatement);
            DBUtils.closeResource(rs);
        } 
        
        return students;        
    }
    
    //add new student
    public static void addNewStudent(String studentFName,
        String studentLName,
        String supervisor,
        String semester,
        int year,
        String supportType,
        String funding,
        int monthlyPayGRA,
        int monthlyPayGTA,
        String section,
        String type,
        String resource){
        
        String studentId = "" + studentFName.toUpperCase().charAt(0) + 
                studentLName.toUpperCase().charAt(0) + (getMaxNum() + 1); 
        
        // declare prepared statement
        PreparedStatement preparedStatement = null;
        
        // declare connection
        Connection conn = null;
        try {
            // get connection
            conn = DBUtils.getConnection();

            // set prepared statement
            preparedStatement = conn
                    .prepareStatement("insert into `phdstudent` (" +
                        "	`StudentId` ," +
                        "	`FName` ," +
                        "	`LName` ," +
                        "	`StSem` ," +
                        "	`StYear` ," +
                        "	`Supervisor`) values (?,?,?,?,?,?);");
            
            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, studentFName);
            preparedStatement.setString(3, studentLName);
            preparedStatement.setString(4, semester);
            preparedStatement.setInt(5, year);
            preparedStatement.setString(6, supervisor);
            
            preparedStatement.executeUpdate();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            DBUtils.closeResource(conn);
            DBUtils.closeResource(preparedStatement);
        } 
        
        if (supportType.equals("GRA")){
            
            try {
                // get connection
                conn = DBUtils.getConnection();

                // set prepared statement
                preparedStatement = conn
                        .prepareStatement("insert into `gra` (" +
                            "	`StudentId` ," +
                            "	`Funding` ," +
                            "	`MonthlyPay` ) values (?,?,?);");

                preparedStatement.setString(1, studentId);
                preparedStatement.setString(2, funding);
                preparedStatement.setInt(3, monthlyPayGRA);

                preparedStatement.executeUpdate();

            }catch(Exception ex){
                ex.printStackTrace();
            }
            finally {
                DBUtils.closeResource(conn);
                DBUtils.closeResource(preparedStatement);
            } 
        }else if (supportType.equals("GTA")){
            try {
                // get connection
                conn = DBUtils.getConnection();

                // set prepared statement
                preparedStatement = conn
                        .prepareStatement("insert into `gta` (" +
                            "	`StudentId` ," +
                            "	`SectionId` ," +
                            "	`MonthlyPay` ) values (?,?,?);");

                preparedStatement.setString(1, studentId);
                preparedStatement.setString(2, section);
                preparedStatement.setInt(3, monthlyPayGTA);

                preparedStatement.executeUpdate();

            }catch(Exception ex){
                ex.printStackTrace();
            }
            finally {
                DBUtils.closeResource(conn);
                DBUtils.closeResource(preparedStatement);
            } 
        }else if (supportType.equals("scholarship")){
             try {
                // get connection
                conn = DBUtils.getConnection();

                // set prepared statement
                preparedStatement = conn
                        .prepareStatement("insert into `scholarshipsupport` (" +
                            "	`StudentId` ," +
                            "	`Type` ," +
                            "	`Source` ) values (?,?,?);");

                preparedStatement.setString(1, studentId);
                preparedStatement.setString(2, type);
                preparedStatement.setString(3, resource);

                preparedStatement.executeUpdate();

            }catch(Exception ex){
                ex.printStackTrace();
            }
            finally {
                DBUtils.closeResource(conn);
                DBUtils.closeResource(preparedStatement);
            }
        }else{//self
            try {
                // get connection
                conn = DBUtils.getConnection();

                // set prepared statement
                preparedStatement = conn
                        .prepareStatement("insert into `selfsupport` (" +
                            "	`StudentId`) values (?);");

                preparedStatement.setString(1, studentId);

                preparedStatement.executeUpdate();

            }catch(Exception ex){
                ex.printStackTrace();
            }
            finally {
                DBUtils.closeResource(conn);
                DBUtils.closeResource(preparedStatement);
            }
        }
    }
    
    //get maximum number from row in student
    //assume that this is unique
    private static int getMaxNum(){
        
        int maxNum = 0;
        
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
                    .prepareStatement("SELECT MAX(SUBSTRING(StudentId, 3)) from phdstudent;");
            
                      rs = preparedStatement.executeQuery();
            
            //iterate and create the StudentInfo objects
            if (rs.next()) {
                
                maxNum = rs.getInt(1);

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            DBUtils.closeResource(conn);
            DBUtils.closeResource(preparedStatement);
            DBUtils.closeResource(rs);
        } 
        
        return maxNum;   
    }
    
}
