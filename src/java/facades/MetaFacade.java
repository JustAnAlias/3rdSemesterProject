/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.AirlineEntity;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 *
 * @author Eske Wolff
 */
public class MetaFacade {

    AirlineFacade af = new AirlineFacade();

    public String getFlights(String from, String date, int tickets) {

        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        BufferedReader reader = null;
        List listAirline = af.getActiveAirlines();
        for (Object a : listAirline) {

            try {
                AirlineEntity ae = (AirlineEntity)a;
                String myurl = ae.getUrl() + "/api/flightinfo/" + from + "/" + date + "/" + tickets;
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
        return sb.toString();
    }
}
