package de.bund.bfr.rakip.vocabularies.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class ParameterDatatype implements FskmlObject {

	private final int id;
	private final String name;
	private final String comment;
	private final String representedAs;

	@JsonCreator
	public ParameterDatatype(@JsonProperty("id") int id,
							 @JsonProperty("name") String name,
							 @JsonProperty("comment") String comment,
							 @JsonProperty("representedAs") String representedAs) {
		this.id = id;
		this.name = name;
		this.comment = comment;
		this.representedAs = representedAs;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getComment() {
		return comment;
	}
	
	public String getRepresentedAs() {
		return representedAs;
	}
}
