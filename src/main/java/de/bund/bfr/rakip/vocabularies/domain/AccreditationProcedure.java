package de.bund.bfr.rakip.vocabularies.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class AccreditationProcedure implements FskmlObject {
	
	private final int id;
	private final String mdstat;
	private final String name;

	@JsonCreator
	public AccreditationProcedure(@JsonProperty("id") int id,
								  @JsonProperty("mdstat") String mdstat,
								  @JsonProperty("name") String name) {
		this.id = id;
		this.mdstat = mdstat;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getMdstat() {
		return mdstat;
	}
	
	public String getName() {
		return name;
	}
}
