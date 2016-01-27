<%-- 
    Document   : index
    Created on : 25-01-2016, 08:26:52 PM
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
            <div style="text-align: center; margin-top: 20px;">
                <img src="resources/mwb.PNG">
                <form action="./consultaServlet" >
                    <p>
                        <input id="Buscando" type="text" name="txtConsulta" style="width: 700px;"/>
                        <input id="BtnBuscar" type="submit" value="Buscar"/>
                    </p>
            </div>
            <div style="margin-left: 100px;">
                <div data-bind="foreach: resultados">
                    <a data-bind="text: link, attr: { href: link}"></a><br>
                    <span data-bind="text: resumen"></span><br><br>
                </div>
                </form>
            </div>
            <script type="text/javascript" src="resources/js/libs/knockout-3.4.0.js" ></script>            
            <script type="text/javascript" src="resources/js/indexController.js" ></script>

        </body>

    </html>
</f:view>
