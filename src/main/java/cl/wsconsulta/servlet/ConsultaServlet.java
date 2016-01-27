/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.wsconsulta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Constants;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Javier
 */
@WebServlet(name = "ConsultaServlet", urlPatterns = {"/consultaServlet"})
public class ConsultaServlet extends HttpServlet {
    
    private static String dataBase;
    private static String coleccionIndice;
    private static String coleccionDocumentos;
    private static String indiceInvertido;

    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String txtConsulta = request.getParameter("txtConsulta");
        String txtUpper =  txtConsulta.toUpperCase();
        String query = consultar(txtUpper);  
        String[] token = query.split(",");

        

        
        try (PrintWriter out = response.getWriter()) {
            
            
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Consulta</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div style='text-align: center; margin-top: 20px;'>");
            out.println("<img src='resources/mwb.PNG'>");
            out.println("<form action='./consultaServlet' >");
            out.println("<p>");
            out.println("<input id='Buscando' type='text' name='txtConsulta' value='"+txtConsulta +"' style='width: 700px;'/>");
            out.println("<input id='BtnBuscar' type='submit' value='Buscar'/>");
            out.println("</p>");
            out.println("</div>");
            out.println("</div>");
            out.println("</form>");
            out.println("</div>");
            out.println("<div style='margin-left:70px; margin-top:40px;'>");
            if(txtConsulta.isEmpty()){
            out.println("<h3>No ingreso consulta</h3>");
            }else{
                if(token.length == 0){
                out.println("<h3>No hay referncias</h3>");   
                }
                else{
                    for (String t: token) {
                        out.println("<h3>"+t+"</h3>");
                    }
                }
            }            
            out.println("</div>");            
            out.println("</body>");
            out.println("</html>");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static String consultar(String consulta) throws FileNotFoundException, IOException {
        
        
        DB database;
       
            database = null;
            
                dataBase = "labsd";
                indiceInvertido = "prueba.xml";
                coleccionDocumentos = "documentos";
                coleccionIndice = "indiceInvertido";

                MongoClient mongoClient = new MongoClient();
                database = mongoClient.getDB(dataBase);


        

        DBCollection indiceInvertido = database.getCollection(coleccionIndice);
        DBCollection documento = database.getCollection(coleccionDocumentos);
         BasicDBList privileges = new BasicDBList();
        


            BasicDBObject query = new BasicDBObject("palabra", consulta);
            DBCursor cursor = indiceInvertido.find(query);
            if (cursor.count() == 0) {
                System.out.println("Busqueda sin resultados: " + consulta);
            } else {
                while (cursor.hasNext()) {

                    privileges = (BasicDBList) cursor.next().get("documento");
 

                    //DBObject obj = cursor.next();
                    //Object value = obj.get("documento");
                    //System.out.println(value);
                }
            }
        String lista = privileges.toString();
        return lista;

    }    
}
