/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.ForwardReservationRequest;
import entity.ReservationRequestEntity;
import entity.ReservationResponseEntity;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 *
 * @author Eske Wolff
 */
public class ReservationCaller implements Callable {

    private ReservationRequestEntity rre;
    AirlineFacade af;
    Gson gson;
    ReservationResponseEntity result = null;

    public ReservationCaller(ReservationRequestEntity rre) {
        this.rre = rre;
        af = new AirlineFacade();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public ReservationResponseEntity call() throws Exception {
        String myurl = "";
        String airlineName = rre.getAirlineName();
        String response = "";
        System.out.println("airlineName: " + airlineName);
        String baseUrl = af.getAirlineByName(airlineName);
        boolean larsFuckedItUp = baseUrl.equals("http://angularairline-plaul.rhcloud.com");

        String json = makeNewJson(rre);
        try {
            if (larsFuckedItUp) {
                myurl = baseUrl + "/api/flightreservation";
            } else {
                myurl = baseUrl + "/api/reservation/" + rre.getFlightID();
            }
            HttpURLConnection con = (HttpURLConnection) new URL(myurl).openConnection();
            con.setRequestProperty("Content-Type", "application/json;");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Method", "POST");
            con.setDoOutput(true);
            PrintWriter pw = new PrintWriter(con.getOutputStream());
            try (OutputStream os = con.getOutputStream()) {
                os.write(json.getBytes("UTF-8"));
            }
            int HttpResult = con.getResponseCode();
            InputStreamReader is = HttpResult < 400 ? new InputStreamReader(con.getInputStream(), "utf-8")
                    : new InputStreamReader(con.getErrorStream(), "utf-8");
            Scanner responseReader = new Scanner(is);

            while (responseReader.hasNext()) {
                response += responseReader.nextLine() + System.getProperty("line.separator");
            }

            if (HttpResult < 400) {
                result = gson.fromJson(response, ReservationResponseEntity.class);
            }
        } catch (Exception e) {
            
        }

        return result;
    }

    public String makeNewJson(ReservationRequestEntity rre) {
        ForwardReservationRequest frr = new ForwardReservationRequest();
        frr.setFlightID(rre.getFlightID());
        frr.setNumberOfSeats(rre.getNumberOfSeats());
        frr.setReserveeEmail(rre.getReserveeEmail());
        frr.setReserveeName(rre.getReserveeName());
        frr.setPassengers(rre.getPassengers());
        frr.setReservePhone(rre.getReservePhone());
        return gson.toJson(frr);

    }

}
