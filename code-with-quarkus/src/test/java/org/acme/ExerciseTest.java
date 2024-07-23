package org.acme;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ExerciseTest {
    @Test
    public void testExerciseEndpoint(){

        given()
            .when().get("/labseq/0")
            .then()
                .statusCode(200)
                .body(is(BigInteger.ZERO.toString()));

        given()
            .when().get("/labseq/1")
            .then()
                .statusCode(200)
                .body(is(BigInteger.ONE.toString()));

        given()
            .when().get("/labseq/2")
            .then()
                .statusCode(200)
                .body(is(BigInteger.ZERO.toString()));

        given()
            .when().get("/labseq/3")
            .then()
                .statusCode(200)
                .body(is(BigInteger.ONE.toString()));

        given()
            .when().get("/labseq/10")
            .then()
                .statusCode(200)
                .body(is(new BigInteger("3").toString()));

        given()
            .when().get("/labseq/-1")
            .then()
                .statusCode(400)
                .body(is("The parameter n must be a non-negative integer."));
    
        given()
            .when().get("/labseq/-10")
            .then()
                .statusCode(400)
                .body(is("The parameter n must be a non-negative integer."));
        
        given()
            .when().get("/labseq/abc")
            .then()
                .statusCode(400);

        given()
            .when().get("/labseq/10.5")
            .then()
                .statusCode(400);
        }

}
