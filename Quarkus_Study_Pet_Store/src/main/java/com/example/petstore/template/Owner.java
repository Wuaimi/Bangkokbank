package com.example.petstore.template;

import java.util.List;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

@Schema(name = "Owner")
public class Owner {

    @Schema(required = true, description = "Owner id")
    @JsonProperty("owner_id")
    private Integer ownerId;

    @Schema(required = true, description = "Owner name")
    @JsonProperty("owner_name")
    private String ownerName;

    @Schema(required = true, description = "Owner age")
    @JsonProperty("owner_age")
    private Integer ownerAge;

    @Schema(required = true, description = "Owner Gender")
    @JsonProperty("owner_gender")
    private String ownerGender;

    @Schema(required = true, description = "Owner Pets")
    @JsonProperty("owner_pets")
    private List<Integer> ownerPets;

    // Default constructor (required by Jackson)
    public Owner() {
    }

    // Full constructor
    public Owner(Integer ownerId, String ownerName, Integer ownerAge, String ownerGender, List<Integer> ownerPets) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.ownerAge = ownerAge;
        this.ownerGender = ownerGender;
        this.ownerPets = ownerPets;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getOwnerAge() {
        return ownerAge;
    }

    public void setOwnerAge(Integer ownerAge) {
        this.ownerAge = ownerAge;
    }

    public String getOwnerGender() {
        return ownerGender;
    }

    public void setOwnerGender(String ownerGender) {
        this.ownerGender = ownerGender;
    }

    public List<Integer> getOwnerPets() {
        return ownerPets;
    }

    public void setOwnerPets(List<Integer> ownerPets) {
        this.ownerPets = ownerPets;
    }
}
