package rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("demoadmin")
@RolesAllowed("Admin")
public class Admin {
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSomething(){
    String now = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
    String result = "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated ADMINS\",\n"
            +"\"serverTime\": \""+now +"\"}";
    return Response.status(Response.Status.OK).entity(result).build();
  }
 
}
