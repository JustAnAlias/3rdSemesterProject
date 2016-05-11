package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.AirlineEntity;
import exceptions.CouldNotAddEntityException;
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
    public Response getAirlines() {
        try{
            return Response.status(Response.Status.OK).entity(gson.toJson(af.getActiveAirlines())).build();
        }
        catch (Exception e){
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Could not connect to database..").build();
        }
        

    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAirlines() {
        try{
            return Response.status(Response.Status.OK).entity(gson.toJson(af.getAirlines())).build();
        }
        catch (Exception e){
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
        

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAirline(String json) throws CouldNotAddEntityException {
        
        AirlineEntity ae = gson.fromJson(json, AirlineEntity.class);
        af.addEntity(ae);

        return Response.status(Response.Status.OK).build();
    }

}
