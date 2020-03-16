<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>Doctoral</title>
    <meta name="Title" content="Doctoral">
    <meta name="Description" content="Doctoral">
    <link href="styles/styles.css" rel="stylesheet">
</head>
<%   
    String studentFName = (String) request.getAttribute("studentFName");
    String studentLName = (String) request.getAttribute("studentLName");
    String message = (String) request.getAttribute("message");
    
    if (studentFName == null) {
        studentFName = "";
    }
    if (studentLName == null) {
        studentLName = "";
    }
    if (message == null) {
        message = "";
    }
%>
<body>
    <div id="wraper">
        <div id="header">                
            <header>
                <div id="logoSection">
                    <div id="logo"><a href="#"><img src="images/logo.png" alt="Doctoral"></a></div>                    
                </div>                                  
            </header>
            <nav id="nav">
                <%@include file="menu.jsp" %>
            </nav>                
        </div>        
        <div class="centredContainer">   
            <div>
                <form action="SearchStudent" method="post">
                    Student's first name: 
                    <input maxlength="120" style="width:200px;" type="text" id="studentFName" name="studentFName" value = "<%= studentFName%>"/>
                    Student's last name: 
                    <input maxlength="120" style="width:200px;" type="text" id="studentLName" name="studentLName" value = "<%= studentLName%>"/>
                    <button type="submit">Search</button>
                </form>
            </div>
            <hr/>
            <table style='width: 100%;'>
                <tr>
                    <th>Supervisor</th>
                    <th>Type of support</th>
                    <th>Milestone name</th>
                    <th>Date passed </th>
                </tr>
                <c:forEach items="${students}" var="student">
                <tr>
                    <td>
                    <c:out value="${student.supervisor}" />                                   
                    </td>
                    <td>
                    <c:out value="${student.supportType}" />                                   
                    </td>
                    <td>
                    <c:out value="${student.milestoneName}" />                                   
                    </td>
                    <td>
                    <c:out value="${student.datePassed}" />                                   
                    </td>
                </tr>
                </c:forEach>
            </table> 
        </div>    
        
        <footer>
            <div id="footer">   
            </div>
        </footer>
    </div>
</body>
</html>