/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Javier
 */
@WebService(serviceName = "search")
public class search {

    /**
     * Buscador por consultas
     */
    
    @WebMethod(operationName = "query")
    public String query(@WebParam(name = "issue") String issue) {
        return issue;
    }
}
