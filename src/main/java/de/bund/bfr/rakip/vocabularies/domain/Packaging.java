package de.bund.bfr.rakip.vocabularies.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Packaging implements FskmlObject {

    private final int id;
    private final String name;
    private final String ssd;
    private final String comment;

    @JsonCreator
    public Packaging(@JsonProperty("id") int id,
                     @JsonProperty("name") String name,
                     @JsonProperty("ssd") String ssd,
                     @JsonProperty("comment") String comment) {
        this.id = id;
        this.name = name;
        this.ssd = ssd;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }
}
