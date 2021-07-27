package com.textoit.resource;

import com.textoit.entity.Movie;
import com.textoit.repository.MovieRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/movies")
@Tag(name = "Movies")
public class MovieResource {

    @Inject
    MovieRepository movieRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Persist movie")
    public void persist(Movie movie) {
        movieRepository.persist(movie);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update movie")
    public void update(Movie movie) {
        movieRepository.update(movie);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete movie by ID")
    public void persist(@PathParam("id") Long id) {
        movieRepository.delete(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all movies")
    public Response getAll() {
        return Response.ok(movieRepository.listAll()).build();
    }
}
