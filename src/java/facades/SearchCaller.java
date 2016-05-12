/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.AirlineEntity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

/**
 *
 * @author Michael
 */
public class SearchCaller implements Callable{
    String from = null;
    String to = null;
    String date = null;
    String baseUrl = null;
    int tickets = 0;
    private URLConnection urlConn;
    private BufferedReader reader;
    private StringBuilder sb;
    boolean larsHasBadProtocol;

    public SearchCaller(String baseUrl, String from, String to, String date, int tickets) {
        this.baseUrl = baseUrl;
        this.from = from;
        this.to = to;
        this.date = date;
        this.tickets = tickets;
        larsHasBadProtocol = baseUrl.equals("http://angularairline-plaul.rhcloud.com");
        sb = new StringBuilder();
    }
    
    
    
    @Override
    public String call() throws Exception {
        if (baseUrl==null) throw new Exception("Couldn't find the f****ng airline");
        if (from==null) throw new Exception("uhmm.. where are you planning on flying from");
        if (date==null) throw new Exception("when are you going?");
        if (tickets<=0) throw new Exception("how many tickets did you say?");
        
        
        if(to != null){
            
            goThere();
        
        
        }
        else{
            dontCare();
        }
//        System.out.println("StringBuilder Content: " + sb.toString());
        return sb.toString();
    }
    
    private void goThere(){
        try {
            String myUrl;
                if(larsHasBadProtocol){
                    myUrl = baseUrl + "/api/flightinfo/" + from + "/" + to + "/" + date + "/" + tickets;
                }
                else{
                    myUrl = baseUrl + "/api/flights/" + from + "/" + to + "/" + date + "/" + tickets;
                }
                
                URL url = new URL(myUrl);
                urlConn = url.openConnection();
                if (urlConn != null) {
                    urlConn.setReadTimeout(60 * 1000);
                }
                if (urlConn != null && urlConn.getInputStream() != null) {
                    reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                    if (reader != null) {
                        int cp;
                        while ((cp = reader.read()) != -1) {
                            sb.append((char) cp);
                        }
                        reader.close();
                    }
                }
            } catch (Exception e) {

            }
        
    }
    private void dontCare(){
        try {
            String myUrl;
//                AirlineEntity ae = (AirlineEntity)a;
            if(larsHasBadProtocol){
                    myUrl = baseUrl + "/api/flightinfo/" + from + "/" + date + "/" + tickets;
                }
                else{
                    myUrl = baseUrl + "/api/flights/" + from + "/" + date + "/" + tickets;
                }
                
                URL url = new URL(myUrl);
                urlConn = url.openConnection();
                if (urlConn != null) {
                    urlConn.setReadTimeout(60 * 1000);
                }
                if (urlConn != null && urlConn.getInputStream() != null) {
                    reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                    if (reader != null) {
                        int cp;
                        while ((cp = reader.read()) != -1) {
                            sb.append((char) cp);
                        }
                        reader.close();
                    }
                }
            } catch (Exception e) {

            }
    }
    
}
