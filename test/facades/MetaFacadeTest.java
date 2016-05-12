/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mr
 */
public class MetaFacadeTest {
    String expected = "{\n" +
"  \"airline\": \"AngularJS Airline\",\n" +
"  \"flights\": [\n" +
"    {\n" +
"      \"flightID\": \"3256-1463094000000\",\n" +
"      \"numberOfSeats\": 2,\n" +
"      \"date\": \"2016-05-12T19:00:00.000Z\",\n" +
"      \"totalPrice\": 100.0,\n" +
"      \"traveltime\": 90,\n" +
"      \"origin\": \"CPH\",\n" +
"      \"destination\": \"STN\",\n" +
"      \"flightNumber\": \"COL3256\"\n" +
"    },\n" +
"    {\n" +
"      \"flightID\": \"3256-1463061600000\",\n" +
"      \"numberOfSeats\": 2,\n" +
"      \"date\": \"2016-05-12T10:00:00.000Z\",\n" +
"      \"totalPrice\": 130.0,\n" +
"      \"traveltime\": 90,\n" +
"      \"origin\": \"CPH\",\n" +
"      \"destination\": \"STN\",\n" +
"      \"flightNumber\": \"COL3256\"\n" +
"    }\n" +
"  ]\n" +
"}";
    public MetaFacadeTest() {
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
     * Test of getFlights method, of class MetaFacade.
     */
    @Test
    public void testGetFlights() throws Exception {
        // This will fail if more Airlines are added, or if AngularJS airline is removed from the airline table
        System.out.println("getFlights");
        String from = "CPH";
        String to = "STN";
        String date = "2016-05-12T22:00:00.000Z";
        int tickets = 2;
        MetaFacade instance = new MetaFacade();
        String expResult = expected;
        String result = instance.getFlights(from, to, date, tickets);
        assertTrue(result.contains(expResult));
    }
    
}

