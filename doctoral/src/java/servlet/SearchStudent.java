package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.StudentService;

/**
 *
 */
@WebServlet(name = "SearchStudent", urlPatterns = {"/SearchStudent"})
public class SearchStudent extends HttpServlet {

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
        javax.servlet.RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
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
        
        //retrieve first name, last name of student
        String studentFName = request.getParameter("studentFName");
        String studentLName = request.getParameter("studentLName");
        
        //query database and put in attribute of request
        request.setAttribute("students", StudentService.searchStudentInfo(studentFName, studentLName));
        
        //store current search keyword
        request.setAttribute("studentFName", studentFName);
        request.setAttribute("studentLName", studentLName);
        
        javax.servlet.RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    
}
