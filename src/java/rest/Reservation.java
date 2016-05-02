package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.AirlineEntity;
import entity.ReservationRequestEntity;
import exceptions.CouldNotAddEntityException;
import facades.AirlineFacade;
import facades.ReservationFacade;
import httpErrors.FlightException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("reservation")

public class Reservation {

    Gson gson;
    ReservationFacade rf;

    public Reservation() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        rf = new ReservationFacade();
    }

    
    @POST
    @Path("{flightId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAirline(@PathParam("flightId")String flightId, String json) throws FlightException {

        System.out.println("FlightID: " + flightId + " Json: " + json);
        ReservationRequestEntity rre = gson.fromJson(json, ReservationRequestEntity.class);
        System.out.println("RRE name: " + rre.getReserveeName());
        String responseString = rf.processRequest(rre);
        return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(responseString).build();
    }

}
