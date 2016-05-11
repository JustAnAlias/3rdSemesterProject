/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import facades.UserFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import entity.User;
import javax.ws.rs.PathParam;
import security.PasswordStorage;
/**
 * REST Web Service
 *
 * @author Eske Wolff
 */
@Path("create")
public class CreateUser{
    
    Gson gson;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public CreateUser() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createUser(String user) throws PasswordStorage.CannotPerformOperationException {
        UserFacade uf = new UserFacade();
        JsonObject jo = new JsonParser().parse(user).getAsJsonObject();
        
        User u = new User();
        u.setUserName(jo.get("userName").getAsString());
        u.setPassword(PasswordStorage.createHash(jo.get("password").getAsString()));
        u.setFirstName(jo.get("firstName").getAsString());
        u.setLastName(jo.get("lastName").getAsString());
        
        uf.addUser(u);
    }
}
