package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import java.beans.Transient;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OwnerResourceTest {

    @Test
    @Order(1)
    public void testGetAllOwners() {
        given()
            .when().get("/v1/owners")
            .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("[0].ownerId", notNullValue())
                .body("[0].ownerAge", notNullValue())
                .body("[0].ownerName", notNullValue())
                .body("[0].ownerGender", notNullValue())
                .body("[0].ownerPets", notNullValue());
    }

    @Test
    @Order(2)
    public void testAddOwner() {
        Map<String, Object> newOwner = new HashMap<>();
        newOwner.put("ownerId", 99);
        newOwner.put("ownerAge", 30);
        newOwner.put("ownerName", "John Doe");
        newOwner.put("ownerGender", "male");
        newOwner.put("ownerPets", Arrays.asList(1, 2));

        given()
            .contentType(ContentType.JSON)
            .body(newOwner)
            .when().post("/v1/owners/add")
            .then()
                .statusCode(200)
                .body("ownerId", equalTo(99))
                .body("ownerName", equalTo("John Doe"));
    }

    @Test
    @Order(3)
    public void testUpdateOwner() {
        Map<String, Object> updatedOwner = new HashMap<>();
        updatedOwner.put("ownerId", 99);
        updatedOwner.put("ownerAge", 40);
        updatedOwner.put("ownerName", "UpdatedUser");
        updatedOwner.put("ownerGender", "other");
        updatedOwner.put("ownerPets", Arrays.asList(3, 4));

        given()
            .contentType(ContentType.JSON)
            .body(updatedOwner)
            .when().put("/v1/owners/update/99")
            .then()
                .statusCode(200)
                .body("ownerAge", equalTo(40))
                .body("ownerName", equalTo("UpdatedUser"));
    }

    @Test
    @Order(4)
    public void testGetOwnerById() {
        given()
            .when().get("/v1/owners/id/99")
            .then()
                .statusCode(200)
                .body("ownerId", equalTo(99));
    }

    @Test
    @Order(4)
    public void testGetOwnerByIdNotFound() {
        given()
            .when().get("/v1/owners/id/9999")
            .then()
                .statusCode(404)
                .body(equalTo("Owner not found"));
    }

    @Test
    @Order(5)
    public void testGetOwnerByAge() {
        given()
            .when().get("/v1/owners/age/35")
            .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("[0].ownerAge", equalTo(35));
    }

    @Test
    @Order(6)
    public void testGetOwnerByName() {
        given()
            .when().get("/v1/owners/name/Kwan")
            .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("[0].ownerName", equalTo("Kwan"));
    }

    @Test
    @Order(7)
    public void testGetOwnerByGender() {
        given()
            .when().get("/v1/owners/gender/male")
            .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("[0].ownerGender", equalTo("male"));
    }

    @Test
    @Order(8)
    public void testGetOwnerByPetID() {
        given()
            .when().get("/v1/owners/pet/3")
            .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("[0].ownerPets", hasItem(3));
    }

    @Test
    @Order(9)
    public void testDeleteOwner() {
        given()
            .when().delete("/v1/owners/delete/99")
            .then()
                .statusCode(200)
                .body(equalTo("true"));
    }

    @Test
    @Order(10)
    public void testDeleteOwnerNotFound() {
        given()
            .when().delete("/v1/owners/delete/99")
            .then()
                .statusCode(404)
                .body(equalTo("false"));
    }

}
