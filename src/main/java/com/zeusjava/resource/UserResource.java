package com.zeusjava.resource;
import com.sun.jersey.api.NotFoundException;
import com.zeusjava.cache.UserCache;
import com.zeusjava.entity.User;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.net.URI;

@Path("/users")
public class UserResource {
    @Context
    UriInfo uriInfo;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") String id) {

        User user = UserCache.getUserCache().get(id);
        if(user==null){
            return new User();
        }
        return user;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void newUser(
            @FormParam("userId") String userId,
            @FormParam("userName") String userName,
            @FormParam("userAge") int userAge,
            @Context HttpServletResponse servletResponse
    ) throws IOException {
        User user = new User(userId,userName,userAge);
        UserCache.getUserCache().put(userId,user);
        URI uri = uriInfo.getAbsolutePathBuilder().path(userId).build();
        Response.created(uri).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUser(@FormParam("userId") String userId,
                            @FormParam("userName") String userName,
                            @FormParam("userAge") int userAge,
                            @PathParam("id") String id) {
        User user = new User(userId, userName, userAge);
        Response res;
        if(UserCache.getUserCache().containsKey(id)) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        UserCache.getUserCache().put(user.getUserId(), user);
        return res;
    }

    @DELETE
    @Path("/{id}")
    public void deleteContact(@PathParam("id") String id) {
        User user = UserCache.getUserCache().remove(id);
        if(user==null)
            throw new NotFoundException("No such User.");
    }




}
