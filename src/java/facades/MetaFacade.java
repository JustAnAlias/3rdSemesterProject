/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.AirlineEntity;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.methods.HttpGet;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Eske Wolff
 */
public class MetaFacade {
    
    AirlineFacade af = new AirlineFacade();
    
    
    public String getFlights(String from, String date, String tickets){
        
        List<String> result = new ArrayList();
        List listAirline = af.getActiveAirlines();
        for (Object a : listAirline) {

                try{
                    
                    AirlineEntity ae = (AirlineEntity)a;
                    String url = ae.getUrl() + "api/" + from + "/" + date + "/" + tickets;
                    CloseableHttpClient httpClient = HttpClients.createDefault();
                    HttpGet httpGet = new HttpGet(url);
                    CloseableHttpResponse response = httpClient.execute(httpGet);
                    
                    System.out.println(response);
                  //  HttpEntity entity = response;
                    
                    
   
                    
                    
                }   catch (IOException ex) {
                Logger.getLogger(MetaFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            
        }
        return "hej";
    }
    
    
//    api/flights/:from/:date/:tickets

    
}
