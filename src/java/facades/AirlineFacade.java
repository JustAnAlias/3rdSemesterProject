package facades;

import entity.AirlineEntity;
import security.IUserFacade;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import openshift_deploy.DeploymentConfiguration;
import security.IUser;
import security.PasswordStorage;

public class AirlineFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    public AirlineFacade() {

    }

    public <T> void addEntity(T ie) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ie);
            em.getTransaction().commit();
        } catch (Exception e) {

        } finally {
            em.close();
        }
    }

    public List<AirlineEntity> getAirlines() {
        EntityManager em = emf.createEntityManager();
        List<AirlineEntity> result = new ArrayList();
        try {
            Query q = em.createQuery("SELECT a FROM AirlineEntity a", AirlineEntity.class);
            result = q.getResultList();
        } finally {
            em.close();
        }
        return result;

    }

    public List<AirlineEntity> getActiveAirlines() {
          EntityManager em = emf.createEntityManager();
        List<AirlineEntity> result = new ArrayList();
        try {
            Query q = em.createQuery("SELECT a FROM AirlineEntity a WHERE a.active =:true", AirlineEntity.class);
            q.setParameter("true", true);
            result = q.getResultList();
        } finally {
            em.close();
        }
        return result;
    }

}