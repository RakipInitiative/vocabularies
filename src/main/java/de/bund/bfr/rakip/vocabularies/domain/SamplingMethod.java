package de.bund.bfr.rakip.vocabularies.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class SamplingMethod implements FskmlObject {

    private final int id;
    private final String name;
    private final String sampmd;
    private final String comment;

    @JsonCreator
    public SamplingMethod(@JsonProperty("id") int id,
                          @JsonProperty("name") String name,
                          @JsonProperty("sampmd") String sampmd,
                          @JsonProperty("comment") String comment) {
        this.id = id;
        this.name = name;
        this.sampmd = sampmd;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSampmd() {
        return sampmd;
    }

    public String getComment() {
        return comment;
    }
}
