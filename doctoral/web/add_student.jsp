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
    String supervisor = (String) request.getAttribute("supervisor");
    String semester = (String) request.getAttribute("semester");
    String year = (String) request.getAttribute("year");
    String supportType = (String) request.getAttribute("year");
    String monthlyPayGRA = (String) request.getAttribute("monthlyPayGRA");
    String monthlyPayGTA = (String) request.getAttribute("monthlyPayGTA");
    String funding = (String) request.getAttribute("funding");
    String section = (String) request.getAttribute("section");
    
    //scholarship
    String type = (String) request.getAttribute("type");
    String source = (String) request.getAttribute("source");
            
    String message = (String) request.getAttribute("message");
    
    if (studentFName == null) {
        studentFName = "";
    }
    if (studentLName == null) {
        studentLName = "";
    }
    if (supervisor == null) {
        supervisor = "";
    }
    if (semester == null) {
        semester = "";
    }
    if (year == null) {
        year = "";
    }
    if (supportType == null) {
        supportType = "";
    }
    if (monthlyPayGRA == null) {
        monthlyPayGRA = "";
    }
    if (monthlyPayGTA == null) {
        monthlyPayGTA = "";
    }
    if (section == null) {
        section = "";
    }
    if (funding == null) {
        funding = "";
    }
    if (type == null) {
        type = "";
    }
    if (source == null) {
        source = "";
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
        <section> 
            <div class="centredContainer">  
                <h2>Insert New Student</h2>
                <form action="InsertNewStudent" method="post">
                    <table>
                        <tr>
                            <td colspan="2">
                                <span style="color: red;"><%= message%></span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                First Name:
                            </td>
                            <td>
                                <input maxlength="120" style="width:200px;" type="text" id="studentFName" name="studentFName" value = "<%= studentFName%>"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Last Name:
                            </td>
                            <td>
                                <input maxlength="120" style="width:200px;" type="text" id="studentLName" name="studentLName" value = "<%= studentLName%>"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Supervisor:
                            </td>
                            <td>
                                <select id='supervisor' name='supervisor'>
                                    <c:forEach items="${supervisors}" var="supervisorVar">
                                        <option ${supervisorVar.instructorID == supervisor? 'selected="selected"' : ''}
                                                value="${supervisorVar.instructorID}">${supervisorVar.firstName} ${supervisorVar.lastName}</option>
                                    </c:forEach>
                                </select>  
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Semester:
                            </td>
                            <td>
                                <input maxlength="120" style="width:200px;" type="text" id="semester" name="semester" value = "<%= semester%>"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Year:
                            </td>
                            <td>
                                <input maxlength="60" style="width:100px;" type="text" id="year" name="year" value = "<%= year%>"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Type of support:
                            </td>
                            <td>
                                <table>
                                <tr>
                                    <td>
                                        <input type="radio" name="supportType" ${supportType == "GRA"? 'checked' : ''} value="GRA">GRA
                                    </td>
                                    <td>
                                        Funding:<br/>
                                        <select id='funding' name='funding'>
                                            <c:forEach items="${fundings}" var="fundingVar">
                                                <option ${fundingVar.accountID == funding? 'selected="selected"' : ''}
                                                        value="${fundingVar.accountID}">${fundingVar.title} (${fundingVar.type}) ${fundingVar.source} (${fundingVar.startDate} - ${fundingVar.endDate}), Start amount: ${fundingVar.startAmount}, Current balance: ${fundingVar.currentBalance}</option>
                                            </c:forEach>
                                        </select>  
                                        <br/>
                                        Monthly pay:<br/>
                                        <input maxlength="60" style="width:100px;" type="text" id="monthlyPayGRA" name="monthlyPayGRA" value = "<%= monthlyPayGRA%>"/>

                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="radio" name="supportType" ${supportType == "GTA"? 'checked' : ''} value="GTA">GTA
                                    </td>
                                    <td>
                                        Section:<br/>
                                        <select id='section' name='section'>
                                            <c:forEach items="${sections}" var="sectionVar">
                                                <option ${sectionVar.sectionID == section? 'selected="selected"' : ''}
                                                        value="${sectionVar.sectionID}">${sectionVar.sectionID} (Course ID: ${sectionVar.courseID}, Course name: ${sectionVar.courseName})</option>
                                            </c:forEach>
                                        </select>  
                                        <br/>
                                        Monthly pay:<br/>
                                        <input maxlength="60" style="width:100px;" type="text" id="monthlyPayGTA" name="monthlyPayGTA" value = "<%= monthlyPayGTA%>"/>

                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="radio" name="supportType" ${supportType == "scholarship"? 'checked' : ''} value="scholarship">scholarship
                                    </td>
                                    <td>
                                        Type:<br/>
                                        <input style="width:300px;" type="text" id="type" name="type" value = "<%= type%>"/>

                                        <br/>Source:<br/>
                                        <input style="width:500px;" type="text" id="source" name="source" value = "<%= source%>"/>

                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="radio" name="supportType" ${supportType == "self"? 'checked' : ''} value="self">self
                                    </td>
                                    <td>

                                    </td>
                                </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>

                            </td>
                            <td>
                                <button type="submit">Add New Student</button>
                            </td>
                        </tr>
                    </table>
                </form> 
            </div>    
        </section>
        <footer>
            <div id="footer">   
            </div>
        </footer>
    </div>
</body>
</html>