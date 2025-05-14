package com.example.petstore;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.petstore.data.OwnerData;
import com.example.petstore.template.Owner;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/v1/owners")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OwnerResource {

    private OwnerData ownerList = OwnerData.getAllOwners();

    @GET
    @APIResponse(responseCode = "200", description = "List of owners", 
        content = @Content(schema = @Schema(implementation = Owner.class)))
    public Response getOwners() {
        return Response.ok(ownerList.getOwnerList()).build();
    }

    @POST
    @Path("add")
    @APIResponse(responseCode = "200", description = "Owner added successfully", 
        content = @Content(schema = @Schema(implementation = Owner.class)))
    public Response addOwner(
        @RequestBody(
            description = "New owner to add", 
            required = true,
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "NewOwnerExample",
                    summary = "Example new owner",
                    value = "{ \"ownerId\": 123, \"ownerAge\": 30, \"ownerName\": \"John Doe\", \"ownerGender\": \"male\", \"ownerPets\": [1, 2] }"
                )
            )
        ) Owner newOwner) {

        Owner newestOwner = ownerList.addOwner(newOwner);
        return Response.ok(newestOwner).build();
    }

    @PUT
    @Path("update/{ownerId}")
    @APIResponse(responseCode = "200", description = "Owner updated successfully", 
        content = @Content(schema = @Schema(implementation = Owner.class)))
    public Response updateOwner(
        @PathParam("ownerId") Integer ownerId,
        @RequestBody(
            description = "Updated owner details",
            required = true,
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "UpdateOwnerExample",
                    summary = "Example update owner",
                    value = "{ \"ownerId\": 123, \"ownerAge\": 40, \"ownerName\": \"UpdatedUser\", \"ownerGender\": \"other\", \"ownerPets\": [3, 4] }"
                )
            )
        ) Owner updatedOwner) {

        Owner editedOwner = ownerList.updateOwner(ownerId, updatedOwner);
        if (editedOwner != null) {
            return Response.ok(editedOwner).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Owner not found").build();
    }

    @DELETE
    @Path("delete/{ownerId}")
    @APIResponse(responseCode = "200", description = "Owner deleted successfully", 
        content = @Content(schema = @Schema(implementation = Boolean.class)))
    public Response deleteOwner(@PathParam("ownerId") Integer ownerId) {
        boolean result = ownerList.deleteOwner(ownerId);
        return Response.ok(result).build();
    }

    @GET
    @Path("id/{ownerId}")
    @APIResponse(responseCode = "200", description = "Owner by ID", 
        content = @Content(schema = @Schema(implementation = Owner.class)))
    public Response getOwnerById(@PathParam("ownerId") Integer ownerId){
        Owner result = ownerList.getOwnerById(ownerId);
        if (result != null) {
            return Response.ok(result).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Owner not found").build();
    }

    @GET
    @Path("age/{ownerAge}")
    @APIResponse(responseCode = "200", description = "Owners by Age", 
        content = @Content(schema = @Schema(implementation = Owner.class)))
    public Response getOwnerByAge(@PathParam("ownerAge") Integer ownerAge){
        List<Owner> result = ownerList.getOwnerByAge(ownerAge);
        return Response.ok(result).build();
    }

    @GET
    @Path("name/{ownerName}")
    @APIResponse(responseCode = "200", description = "Owners by Name", 
        content = @Content(schema = @Schema(implementation = Owner.class)))
    public Response getOwnerByName(@PathParam("ownerName") String ownerName){
        List<Owner> result = ownerList.getOwnerByName(ownerName);
        return Response.ok(result).build();
    }

    @GET
    @Path("gender/{ownerGender}")
    @APIResponse(responseCode = "200", description = "Owners by Gender", 
        content = @Content(schema = @Schema(implementation = Owner.class)))
    public Response getOwnerByGender(@PathParam("ownerGender") String ownerGender){
        List<Owner> result = ownerList.getOwnerByGender(ownerGender);
        return Response.ok(result).build();
    }

    @GET
    @Path("pet/{petId}")
    @APIResponse(responseCode = "200", description = "Owners by Pet ID", 
        content = @Content(schema = @Schema(implementation = Owner.class)))
    public Response getOwnerByPetID(@PathParam("petId") Integer petId){
        List<Owner> result = ownerList.getOwnerByPetId(petId);
        return Response.ok(result).build();
    }
}
