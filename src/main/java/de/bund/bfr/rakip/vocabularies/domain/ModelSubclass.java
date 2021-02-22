package de.bund.bfr.rakip.vocabularies.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class ModelSubclass implements FskmlObject {

    private final int id;
    private final String name;
    private final ModelClass classCategory;

    @JsonCreator
    public ModelSubclass(@JsonProperty("id") int id,
                         @JsonProperty("name") String name,
                         @JsonProperty("classCategory") ModelClass classCategory) {
        this.id = id;
        this.name = name;
        this.classCategory = classCategory;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ModelClass getClassCategory() {
        return classCategory;
    }
}
