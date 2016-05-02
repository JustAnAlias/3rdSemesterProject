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
import exceptions.CouldNotAddEntityException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import openshift_deploy.DeploymentConfiguration;

/**
 *
 * @author Michael
 */
public class ReservationFacade {

    Gson gson;
    AirlineFacade af;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    public ReservationFacade() {
        af = new AirlineFacade();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public <T> void addEntity(T ie) throws CouldNotAddEntityException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ie);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new CouldNotAddEntityException("Could not add " + ie.getClass().getCanonicalName() + " to the database!");
        } finally {
            em.close();
        }
    }

    public String processRequest(ReservationRequestEntity rre) {

         System.out.println("RRE name 2 : " + rre.getReserveeName());
        String airlineName = rre.getAirlineName();
        String response = "";
        System.out.println("airlineName: " + airlineName);
        String baseUrl = af.getAirlineByName(airlineName);
        System.out.println("baseUrl: " + baseUrl);
        String json = makeJsonShit(rre);
        System.out.println("Trying to get reservation.. " + airlineName + " BaseURL: " + baseUrl);
        System.out.println("JSON: " + json);
        try {

            String myurl = baseUrl + "/api/reservation/" + rre.getFlightId();
            HttpURLConnection con = (HttpURLConnection) new URL(baseUrl).openConnection();
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
            System.out.println(response);
            System.out.println(con.getResponseCode());
            System.out.println(con.getResponseMessage());
        } catch (Exception e) {

        } finally {
            try {
                addEntity(rre);
            } catch (Exception e) {

            }
        }

        return response;
    }

    public String makeJsonShit(ReservationRequestEntity rre) {
        ForwardReservationRequest frr = new ForwardReservationRequest();
        frr.setFlightId(rre.getFlightId());
        frr.setNumberOfSeats(rre.getNumberOfSeats());
        frr.setReserveeEmail(rre.getReserveeEmail());
        frr.setReserveeName(rre.getReserveeName());
        frr.setPassengers(rre.getPassengers());
        frr.setReserveePhone(rre.getReserveePhone());
        return gson.toJson(frr);

    }
}
