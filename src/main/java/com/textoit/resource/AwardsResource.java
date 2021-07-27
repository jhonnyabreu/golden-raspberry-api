package com.textoit.resource;

import com.textoit.entity.dto.AwardsOutput;
import com.textoit.repository.AwardsRepository;
import com.textoit.repository.MovieRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/awards")
@Tag(name = "Awards")
public class AwardsResource {

    @Inject
    AwardsRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Search top rated and least rated awards")
    public Response searchAwards() {
        AwardsOutput output = repository.searchAwards();
        return Response.ok(output).build();
    }
}