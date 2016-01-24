<%-- 
    Document   : index
    Created on : 23-01-2016, 10:51:34 PM
    Author     : Javier
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>MyWebBrowser</title>
        </head>
        <body>
            <div style="text-align: center; margin-top: 100px;">
                <img src="resources/mwb.PNG" />
                <form action="index.jsp" method="POST">
                    <input type="text" name="search" style="width: 700px;">
                    <input type="submit" value="Buscar">
                </form>
            </div>    
            <%-- start web service invocation --%><hr/>
            <%
                try {
                    ws.client.Search_Service service = new ws.client.Search_Service();
                    ws.client.Search port = service.getSearchPort();
                    String req = " ";
                    String result = port.query(req);
                    out.println(result);
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
            %>
            <%-- end web service invocation --%><hr/>

        </body>
    </html>
</f:view>
