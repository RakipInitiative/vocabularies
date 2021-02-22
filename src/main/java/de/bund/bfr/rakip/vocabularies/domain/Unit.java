package de.bund.bfr.rakip.vocabularies.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Unit implements FskmlObject {

    private final int id;
    private final String name;
    private final String ssd;
    private final String comment;
    private final UnitCategory category;

    @JsonCreator
    public Unit(@JsonProperty("id") int id,
                @JsonProperty("name") String name,
                @JsonProperty("ssd") String ssd,
                @JsonProperty("comment") String comment,
                @JsonProperty("category") UnitCategory category) {
        this.id = id;
        this.name = name;
        this.ssd = ssd;
        this.comment = comment;
        this.category = category;
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

    public UnitCategory getCategory() {
        return category;
    }
}
