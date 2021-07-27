package com.textoit.resource;

import com.textoit.entity.Movie;
import com.textoit.repository.MovieRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/movies")
public class MovieResource {

    @Inject
    MovieRepository movieRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void persist(Movie movie) {
        movieRepository.persist(movie);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Movie movie) {
        movieRepository.update(movie);
    }

    @DELETE
    @Path("/{id}")
    public void persist(@PathParam("id") Long id) {
        movieRepository.delete(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(movieRepository.listAll()).build();
    }
}
