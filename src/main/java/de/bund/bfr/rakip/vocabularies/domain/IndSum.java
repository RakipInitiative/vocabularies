package de.bund.bfr.rakip.vocabularies.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/** Hazard ind sum. */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class IndSum implements FskmlObject {

    private final int id;
    private final String name;
    private final String ssd;

    @JsonCreator
    public IndSum(@JsonProperty("id") int id,
                  @JsonProperty("name") String name,
                  @JsonProperty("ssd") String ssd) {
        this.id = id;
        this.name = name;
        this.ssd = ssd;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSsd() {
        return ssd;
    }
}
