package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.AirlineEntity;
import facades.AirlineFacade;
import facades.SearchFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;
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
    SearchFacade mf;

    public MetaSearch() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        mf = new SearchFacade();
    }

    @GET
    @Path("/{from}/{date}/{tickets}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlightsFrom(@PathParam("from") String from, @PathParam("date") String date,
            @PathParam("tickets") int tickets) {
        try {
//            return Response.status(Response.Status.OK).entity(gson.toJson(mf.getFlights(from, null, date, tickets))).build();
            return Response.status(Response.Status.OK).entity(mf.getFlights(from, null, date, tickets)).build();
        } // InterruptedException, ExecutionException, TimeoutException
        catch (TimeoutException tex) {
            return Response.status(Response.Status.REQUEST_TIMEOUT).entity("I couldn't find those f***ing airlines for ya (sorry?)").build();
        } catch (Exception e) {
            return Response.status(Response.Status.SEE_OTHER).entity("oops?").build();
        }
    }

    @GET
    @Path("/{from}/{to}/{date}/{tickets}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlightsFromTo(@PathParam("from") String from,
            @PathParam("to") String to,
            @PathParam("date") String date,
            @PathParam("tickets") int tickets) {
        try {
            return Response.status(Response.Status.OK).entity(mf.getFlights(from, to, date, tickets)).build();
        } catch (TimeoutException tex) {
            return Response.status(Response.Status.REQUEST_TIMEOUT).entity("I couldn't find those f***ing airlines for ya (sorry?)").build();
        } catch (Exception e) {
            return Response.status(Response.Status.SEE_OTHER).entity("oops?").build();
        }
    }
}