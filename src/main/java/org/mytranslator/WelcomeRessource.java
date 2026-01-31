package org.mytranslator;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("welcome")
public class WelcomeRessource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Message welcome(@QueryParam("name") String name) {
        if (name == null || name.trim().isEmpty()) {
            name = "world";
        }
        return new Message(name);
    }
}
