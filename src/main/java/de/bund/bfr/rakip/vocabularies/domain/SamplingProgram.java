package de.bund.bfr.rakip.vocabularies.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/** Type of sampling program. */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class SamplingProgram implements FskmlObject {

    private final int id;
    private final String name;
    private final String progType;

    @JsonCreator
    public SamplingProgram(@JsonProperty("id") int id,
                           @JsonProperty("name") String name,
                           @JsonProperty("progType") String progType) {
        this.id = id;
        this.name = name;
        this.progType = progType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProgType() {
        return progType;
    }
}
