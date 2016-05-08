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
import exceptions.CouldNotAddEntityException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import openshift_deploy.DeploymentConfiguration;

/**
 *
 * @author Michael
 */
public class ReservationFacade {

    Gson gson;
    AirlineFacade af;
    ExecutorService executor;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    public ReservationFacade() {
        af = new AirlineFacade();
        gson = new GsonBuilder().setPrettyPrinting().create();
        executor = Executors.newFixedThreadPool(4);
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
        Future<ReservationResponseEntity> fut = executor.submit(new ReservationCaller(rre));
        ReservationResponseEntity res = null;

        try {
            res = fut.get(10, TimeUnit.SECONDS);
            addEntity(rre);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ReservationFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ReservationFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(ReservationFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CouldNotAddEntityException ex) {
                Logger.getLogger(ReservationFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        //    group.goOutsideToGetFreshAir();
        }

        String result = gson.toJson(res, ReservationResponseEntity.class);
        
        return result;
        
    }

    public List<ReservationRequestEntity> getAllReservations() {
        EntityManager em = emf.createEntityManager();
        List<ReservationRequestEntity> result = new ArrayList();
        try {
            Query q = em.createQuery("SELECT a FROM ReservationRequestEntity a", ReservationRequestEntity.class);
            result = q.getResultList();
        } finally {
            em.close();
        }
        return result;

    }

}
