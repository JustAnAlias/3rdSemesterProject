/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.ReservationRequestEntity;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michael
 */
public class ReservationFacadeTest {
    
    public ReservationFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of processRequest method, of class ReservationFacade.
     */
    @Test
    public void testProcessRequest() {
        System.out.println("processRequest");
        ReservationRequestEntity rre = null;
        ReservationFacade instance = new ReservationFacade();
        String expResult = "";
        String result = instance.processRequest(rre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllReservations method, of class ReservationFacade.
     */
    @Test
    public void testGetAllReservations() {
        System.out.println("getAllReservations");
        ReservationFacade instance = new ReservationFacade();
        List<ReservationRequestEntity> expResult = null;
        List<ReservationRequestEntity> result = instance.getAllReservations();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReservationsByName method, of class ReservationFacade.
     */
    @Test
    public void testGetReservationsByName() {
        System.out.println("getReservationsByName");
        String name = "";
        ReservationFacade instance = new ReservationFacade();
        List<ReservationRequestEntity> expResult = null;
        List<ReservationRequestEntity> result = instance.getReservationsByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
