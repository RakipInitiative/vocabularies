package de.bund.bfr.rakip.vocabularies.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class PublicationType implements FskmlObject {

    private final int id;
    private final String shortName;
    private final String fullName;

    @JsonCreator
    public PublicationType(@JsonProperty("id") int id,
                           @JsonProperty("shortName") String shortName,
                           @JsonProperty("fullName") String fullName) {
        this.id = id;
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }
}