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
public class URLCaller implements Callable{
    String from = null;
    String to = null;
    String date = null;
    String baseUrl = null;
    int tickets = 0;
    private URLConnection urlConn;
    private BufferedReader reader;
    private StringBuilder sb;

    public URLCaller(String baseUrl, String from, String to, String date, int tickets) {
        this.baseUrl = baseUrl;
        this.from = from;
        this.to = to;
        this.date = date;
        this.tickets = tickets;
        sb = new StringBuilder();
    }
    
    
    
    @Override
    public String call() throws Exception {
        if (baseUrl==null) throw new Exception("fucktard!");
        if (from==null) throw new Exception("fucktard!");
        if (date==null) throw new Exception("fucktard!");
        if (tickets<=0) throw new Exception("dumbass!");
        
        
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
                
                String myurl = baseUrl + "/api/flightinfo/" + from + "/" + to + "/" + date + "/" + tickets;
                URL url = new URL(myurl);
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
//                AirlineEntity ae = (AirlineEntity)a;
                String myurl = baseUrl + "/api/flightinfo/" + from + "/" + date + "/" + tickets;
                URL url = new URL(myurl);
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
