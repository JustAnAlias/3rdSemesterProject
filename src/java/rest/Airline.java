package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.AirlineEntity;
import facades.AirlineFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("airline")
//@RolesAllowed("Admin")
public class Airline {

    Gson gson;
    AirlineFacade af;

    public Airline() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        af = new AirlineFacade();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAirlines() {
        return gson.toJson(af.getActiveAirlines());

    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllAirlines() {
        return gson.toJson(af.getAirlines());

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAirline(String json) {

        AirlineEntity ae = gson.fromJson(json, AirlineEntity.class);
        af.addEntity(ae);

        return Response.status(Response.Status.OK).build();
    }

}
