package de.bund.bfr.rakip.vocabularies.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Language implements FskmlObject {

    private final int id;

    /** ISO 639-1 two-letter code. */
    private final String code;

    private final String name;

    @JsonCreator
    public Language(@JsonProperty("id") int id,
                    @JsonProperty("code") String code,
                    @JsonProperty("name") String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
