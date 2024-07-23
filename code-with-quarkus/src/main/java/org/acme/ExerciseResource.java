package org.acme;

import java.math.BigInteger;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/labseq")
public class ExerciseResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{n}")
    @Operation(
        summary = "Calculate Labseq number",
        description = "Returns the n-th Labseq number."
    )
    @APIResponses(
        value = {
            @APIResponse(
                responseCode = "200",
                description = "Sucessful",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = BigInteger.class))),
            @APIResponse(responseCode="400", description= "Invalid input.")
        }
    )
    public Response getLabseq(@PathParam("n") String nStr){
        if (!(nStr != null && nStr.matches("\\d+"))){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The parameter n must be a non-negative integer.")
                    .build(); 
        } ;
        int n;
        try {
            n = Integer.parseInt(nStr);
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The parameter n must be a non-negative integer.")
                    .build();
        }
        if (n < 0){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The parameter n must be a non-negative integer.")
                    .build();
        }

        BigInteger result = calculation(n);
        return Response.ok(result.toString()).build();
    } 

    private BigInteger calculation(int n){

        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;
        if (n == 2) return BigInteger.ZERO;
        if (n == 3) return BigInteger.ONE;
        
        // Create an array to store the values of the sequence
        BigInteger[] labseq = new BigInteger[n + 1];
        labseq[0] = BigInteger.ZERO;
        labseq[1] = BigInteger.ONE;
        labseq[2] = BigInteger.ZERO;
        labseq[3] = BigInteger.ONE;

        for (int i = 4; i <= n; i++) {
            labseq[i] = labseq[i - 4].add(labseq[i - 3]);
        }

        return labseq[n];
    }
}
