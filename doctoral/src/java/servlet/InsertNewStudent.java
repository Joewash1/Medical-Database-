package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.GrantService;
import service.SectionService;
import service.StudentService;
import service.SupervisorService;

/**
 *
 */
@WebServlet(name = "InsertNewStudent", urlPatterns = {"/InsertNewStudent"})
public class InsertNewStudent extends HttpServlet {


     /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("supervisors", SupervisorService.getSupervisors());
        request.setAttribute("fundings", GrantService.getGrants());
        request.setAttribute("sections", SectionService.getSections());
        
        javax.servlet.RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/add_student.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {     
        
        boolean valid = true; //if the param is valid
        
        request.setAttribute("supervisors", SupervisorService.getSupervisors());
        request.setAttribute("fundings", GrantService.getGrants());
        request.setAttribute("sections", SectionService.getSections());
        
         //retrieve first name, last name of student
        String studentFName = request.getParameter("studentFName");
        String studentLName = request.getParameter("studentLName");
        String supervisor = request.getParameter("supervisor");
        
        String semester = request.getParameter("semester");
        String year = request.getParameter("year");
        String supportType = request.getParameter("supportType");
        String funding = request.getParameter("funding");
        String monthlyPayGRA = request.getParameter("monthlyPayGRA");
        String monthlyPayGTA = request.getParameter("monthlyPayGTA");
        String section = request.getParameter("section");
        
        //scholarship
        String type = request.getParameter("type");
        String source = request.getParameter("source");
        
        String message = "";
        int iYear = 0;//year in integer
        int iMonthlyPayGRA = 0; //monthly pay in float
        int iMonthlyPayGTA = 0; //monthly pay in float
        
        //validate input
        if (studentFName.equals("")){
            message = "First name is required";
            valid = false;
        }else if (studentLName.equals("")){
            message = "Last name is required";
            valid = false;
        }else if (semester.equals("")){
            message = "Semester is required";
            valid = false;
        }else if (year.equals("")){
            message = "Year is required";
            valid = false;
        }else{
            try {
                iYear =  Integer.parseInt(year);
            } catch (Exception e) {
                message = "Year must be an integer number";
                valid = false;
            }
        }
        
        if (valid && (supportType == null || supportType.equals(""))){ //type of support
            message = "Type of support is required";
            valid = false;
        }
        
        if (valid && supportType.equals("GRA")){
            try {
                iMonthlyPayGRA =  Integer.parseInt(monthlyPayGRA);
            } catch (Exception e) {
                message = "GRA - Monthly pay must be a number";
                valid = false;
            }
        }
        
        if (valid && supportType.equals("GTA")){
            try {
                iMonthlyPayGTA =  Integer.parseInt(monthlyPayGTA);
            } catch (Exception e) {
                message = "GTA - Monthly pay must be a number";
                valid = false;
            }
        }
        
        if (valid && supportType.equals("scholarship")){
            if (type.equals("")){
                message = "Scholarship - type is required";
                valid = false;
            }else if (source.equals("")){
                 message = "Scholarship - source is required";
                valid = false;
            }
        }
        
        if (valid){
            
            //add to database
            StudentService.addNewStudent(studentFName, studentLName, supervisor, 
                    semester, iYear, supportType, funding, 
                    iMonthlyPayGRA, iMonthlyPayGTA, section, type, source);
            
            message = "New student has been added successfully";
            
        }else{
        
            //store current parameters
            request.setAttribute("studentFName", studentFName);
            request.setAttribute("studentLName", studentLName);
            request.setAttribute("supervisor", supervisor);
            request.setAttribute("semester", semester);
            request.setAttribute("year", year);
            request.setAttribute("supportType", supportType);
            request.setAttribute("funding", funding);
            request.setAttribute("monthlyPayGRA", monthlyPayGRA);
            request.setAttribute("monthlyPayGTA", monthlyPayGTA);
            request.setAttribute("section", section);

            request.setAttribute("type", type);
            request.setAttribute("source", source);            
        }
        
        request.setAttribute("message", message);
        
        javax.servlet.RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/add_student.jsp");
        dispatcher.forward(request, response);
    }
}
