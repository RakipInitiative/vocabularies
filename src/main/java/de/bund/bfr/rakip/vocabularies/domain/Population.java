package de.bund.bfr.rakip.vocabularies.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Population implements FskmlObject {

    private final int id;
    private final String name;
    private final String foodon;

    @JsonCreator
    public Population(@JsonProperty("id") int id,
                      @JsonProperty("name") String name,
                      @JsonProperty("foodon") String foodon) {
        this.id = id;
        this.name = name;
        this.foodon = foodon;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFoodon() {
        return foodon;
    }
}
