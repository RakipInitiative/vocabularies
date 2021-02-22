package de.bund.bfr.rakip.vocabularies.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class SamplingPoint implements FskmlObject {

    private final int id;
    private final String name;
    private final String sampnt;

    @JsonCreator
    public SamplingPoint(@JsonProperty("id") int id,
                         @JsonProperty("name") String name,
                         @JsonProperty("sampnt") String sampnt) {
        this.id = id;
        this.name = name;
        this.sampnt = sampnt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSampnt() {
        return sampnt;
    }
}
