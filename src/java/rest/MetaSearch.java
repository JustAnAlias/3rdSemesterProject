package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.AirlineEntity;
import facades.AirlineFacade;
import facades.MetaFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("meta")
//@RolesAllowed("Admin")
public class MetaSearch {

    Gson gson;
    MetaFacade mf;

    public MetaSearch() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        mf = new MetaFacade();
    }

    @GET
    @Path("/{from}/{date}/{tickets}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAirlines(@PathParam("from") String from, @PathParam("date") String date,
            @PathParam("tickets") int tickets) {
        try{
        return mf.getFlights(from, date, tickets);
        }
        catch(Exception e){
            return new Exception("something went wrong").toString();
        }
    }

}
