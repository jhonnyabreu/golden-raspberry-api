package com.textoit.resource;

import com.textoit.entity.dto.AwardsOutput;
import com.textoit.repository.AwardsRepository;
import com.textoit.repository.MovieRepository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/awards")
public class AwardsResource {

    @Inject
    AwardsRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchAwards() {
        AwardsOutput output = repository.searchAwards();
        return Response.ok(output).build();
    }
}