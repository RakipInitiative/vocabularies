package de.bund.bfr.rakip.vocabularies.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Country implements FskmlObject {

    private final int id;
    private final String name;
    private final String iso;

    @JsonCreator
    public Country(@JsonProperty("id") int id,
                   @JsonProperty("name") String name,
                   @JsonProperty("iso") String iso) {
        this.id = id;
        this.name = name;
        this.iso = iso;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIso() {
        return iso;
    }
}
