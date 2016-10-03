<%-- 
    Document   : add_person
    Created on : Oct 3, 2016, 2:09:44 AM
    Author     : Ben
--%>

<%@page import="java.util.Map"%>
<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Person</title>
        <link rel="stylesheet" href="public/css/theme.css"/>
    </head>
    <body>
        <% if(request.getAttribute("errors") !=null){
                  
            
            %>
            <fieldset>
                <legend>Errors:
                </legend>
                <ul>
                    <%  for(Map.Entry<String, String[]> entry: request.getParameterMap().entrySet()){
                        for(String s: entry.getValue()) {
                    %>
                    <li><%= entry.getKey()%> is empty</li>
                    <% } }%>
                </ul>
                <% } %>
            </fieldset>
        <form name="email_form" action="addPerson" method="post">
            <p>Please enter your e-mail address to subscribe to our newsletter.</p>
            <jsp:useBean id="person" class="com.sample1.models.Person" scope="session"></jsp:useBean>
            <%=person.getEmail()%>
            <p>E-Mail: <input type="text" name="email"  ></p>
            <p><input type="submit" name="" value="Subscribe"></p>
        </form>
    </body>
</html>
