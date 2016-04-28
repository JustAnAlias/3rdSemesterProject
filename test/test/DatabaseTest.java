package test;

import entity.AirlineEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import openshift_deploy.DeploymentConfiguration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseTest {

    AirlineEntity expected = new AirlineEntity();
    static EntityManagerFactory emf;
    static EntityManager em;
    String URL = "www.google.dk";

    public DatabaseTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
        em = emf.createEntityManager();

    }

    @AfterClass
    public static void tearDownClass() {
        em.close();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test 
   public void createAndRead() {

        expected.setUrl(URL);
        expected.setActive(true);
        em.getTransaction().begin();
        em.persist(expected);
        em.getTransaction().commit();

        AirlineEntity actual = new AirlineEntity();
        actual = em.find(AirlineEntity.class, URL);
        Assert.assertEquals(expected, actual);
        delete(URL);

    }

    public void delete(String URL) {

        em.getTransaction().begin();
        AirlineEntity delete = new AirlineEntity();
        delete = em.find(AirlineEntity.class, URL);
        em.remove(delete);
        em.getTransaction().commit();
    }

    
    @Test
    public void CreateAndUpdate() {
        
        expected.setUrl(URL);
        expected.setActive(true);
        em.getTransaction().begin();
        em.persist(expected);
        em.getTransaction().commit();
        em.close();
        
        
        AirlineEntity actual = new AirlineEntity();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        actual = em.merge(em.find(AirlineEntity.class, URL));
        actual.setActive(false);
        em.getTransaction().commit();
        delete(URL);
    }
    
}
