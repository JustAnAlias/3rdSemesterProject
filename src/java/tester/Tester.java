/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import facades.AirlineFacade;
import facades.MetaFacade;

/**
 *
 * @author Eske Wolff
 */
public class Tester {
    public static void main(String[] args) {
        
        AirlineFacade af = new AirlineFacade();
        MetaFacade mf = new MetaFacade();
        
        //Add http://angularairline-plaul.rhcloud.com to database from Postman and it should work.
        System.out.println(mf.getFlights("CPH", "2016-01-01T00:00:00.000Z", 2));
    }
    
}
