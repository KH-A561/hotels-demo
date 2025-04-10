package ru.akhilko.hotelsdemo.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestQuery;
import ru.akhilko.hotelsdemo.dto.HotelCreateRequest;
import ru.akhilko.hotelsdemo.dto.HotelResponse;
import ru.akhilko.hotelsdemo.exception.ResponseStatusException;
import ru.akhilko.hotelsdemo.service.HotelService;

@Path("/hotel")
@Slf4j
@AllArgsConstructor
@Tag(name = "hotel", description = "Hotel operations")
@SecurityScheme(
        securitySchemeName = "openIdConnect",
        type = SecuritySchemeType.OPENIDCONNECT,
        openIdConnectUrl  = "http://localhost:8890/realms/hotels/.well-known/openid-configuration"
)
public class HotelController {
    private final HotelService hotelService;

    @Operation(summary = "Get all Hotels with possible sorting and pagination")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@RestQuery("offset") Integer offset,
                         @RestQuery("limit") Integer limit,
                         @RestQuery("sortByTitle") Boolean isSortedByTitle) {
        log.info("List Hotels offset={} limit={} sortByTitle={}", offset, limit, isSortedByTitle);
        return Response.ok(hotelService.list(offset, limit, isSortedByTitle)).build();
    }

    @Operation(summary = "Create new Hotel")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "successful operation",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HotelResponse.class))
                    }),
            @APIResponse(responseCode = "400", description = "HotelCreateRequest is invalid", content = @Content)})
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@RestForm @PartType(MediaType.APPLICATION_JSON) HotelCreateRequest request) {
        try {
            log.info("Create Hotel {}", request);
            return Response.ok(hotelService.create(request)).build();
        } catch (ResponseStatusException e) {
            log.error("Create Hotel exception code={}, message={}", e.getStatusCode(), e.getMessage());
            return Response.status(e.getStatusCode()).entity(e.getMessage()).build();
        }
    }

    @Operation(summary = "Delete Hotel by its id")
    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") Long id) {
        log.info("Delete Hotel id={}", id);
        hotelService.delete(id);
    }
}
