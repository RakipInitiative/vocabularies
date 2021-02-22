package de.bund.bfr.rakip.vocabularies.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Right implements FskmlObject {

    private int id;
    private String shortname;
    private String fullname;
    private String url;

    @JsonCreator
    public Right(@JsonProperty("id") int id,
                 @JsonProperty("shortname") String shortname,
                 @JsonProperty("fullname") String fullname,
                 @JsonProperty("url") String url) {
        this.id = id;
        this.shortname = shortname;
        this.fullname = fullname;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getShortname() {
        return shortname;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUrl() {
        return url;
    }
}