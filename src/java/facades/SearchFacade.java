/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import entity.AirlineEntity;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Eske Wolff & M!chael Rulle
 */
public class SearchFacade {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    ExecutorService executor;
    AirlineFacade af = new AirlineFacade();
    StringBuilder sb = new StringBuilder();

    public String getFlights(String from, String to, String date, int tickets) throws InterruptedException, ExecutionException, TimeoutException {
        executor = Executors.newFixedThreadPool(4);

        List listAirline = af.getActiveAirlines();
        System.out.println("number of airlines in the database: " + listAirline.size());
        List<Future> futures = new ArrayList();
        for (Object a : listAirline) {
            AirlineEntity ae = (AirlineEntity) a;
            Callable c = new SearchCaller(ae.getUrl(), from, to, date, tickets);
            Future<String> fut = executor.submit(c);
            futures.add(fut);
        }
        List<String> resList = new ArrayList();
        for (Future<String> future : futures) {
            resList.add(future.get(5, TimeUnit.SECONDS));

        }
        String result="";
        
        for (int i = 0; i < resList.size(); i++) {
            if (resList.get(i).length() > 2) {
                result+=(resList.get(i));
            }
            if(resList.size()>i+1){
                result+=",\n";
            }
            
        }
//        String result = sb.toString();
//        if (result.endsWith(",\n")) {
//            result = result.substring(0, result.lastIndexOf(","));
//        }
        result = "[\n" + result + "\n]";
        System.out.println(result);
        return result;
    }
}
