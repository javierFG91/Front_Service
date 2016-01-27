/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.wsconsulta.consulta;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Javier
 */
@WebService(serviceName = "Consulta")
public class Consulta {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        if(txt == null){
            return "Hello Javo !";
        }
        return "Hello " + txt + " !";
    }
    
    private static String dataBase;
    private static String coleccionIndice;
    private static String coleccionDocumentos;
    private static String indiceInvertido;

    @WebMethod(operationName = "consultar")
    public String realizarConsulta(@WebParam(name = "consulta") BasicDBList privileges) throws IOException {        
        DB database;
        try (BufferedReader entrada = new BufferedReader(new FileReader("datos.ini"))) {
            database = null;
            try {
                dataBase = entrada.readLine();
                indiceInvertido = entrada.readLine();
                coleccionDocumentos = entrada.readLine();
                coleccionIndice = entrada.readLine();

                MongoClient mongoClient = new MongoClient();
                database = mongoClient.getDB(dataBase);

            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
            entrada.close();
        }

        DBCollection indiceInvertido = database.getCollection(coleccionIndice);
        DBCollection documento = database.getCollection(coleccionDocumentos);

        while (true) {

            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            String consulta = lector.readLine().toUpperCase();
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
            System.out.println(privileges);    
            }
        }

    }
    
    
}
