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
        int countResults = 0;
        for (Future<String> future : futures) {
            String tmp = future.get(5, TimeUnit.DAYS);
            if (tmp.length() > 10) {
                sb.append(tmp);
                sb.append(",\n");
            }

        }

        String result = sb.toString();
        if (result.endsWith(",\n")) {
            result = result.substring(0, result.lastIndexOf(","));
        }
        result = "[\n" + result + "\n]";
        return result;
    }
}
