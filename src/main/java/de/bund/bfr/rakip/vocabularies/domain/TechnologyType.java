package de.bund.bfr.rakip.vocabularies.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class TechnologyType implements FskmlObject {

	private final int id;
	private final String ssd;
	private final String name;
	private final String comment;

	@JsonCreator
	public TechnologyType(@JsonProperty("id") int id,
						  @JsonProperty("ssd") String ssd,
						  @JsonProperty("name") String name,
						  @JsonProperty("comment") String comment) {
		this.id = id;
		this.ssd = ssd;
		this.name = name;
		this.comment = comment;
	}
	
	public int getId() {
		return id;
	}
	
	public String getSsd() {
		return ssd;
	}
	
	public String getName() {
		return name;
	}
	
	public String getComment() {
		return comment;
	}
}
