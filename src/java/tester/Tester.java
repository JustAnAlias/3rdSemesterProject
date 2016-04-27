/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import entity.AirlineEntity;
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
//        AirlineEntity ae = new AirlineEntity();
//        ae.setUrl("http://angularairline-plaul.rhcloud.com");
//        ae.setActive(true);
//        af.addEntity(ae);
        
        //Add http://angularairline-plaul.rhcloud.com to database from Postman and it should work.
        try{
        System.out.println(mf.getFlights("CPH","STN", "2016-01-01T00:00:00.000Z", 2));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
