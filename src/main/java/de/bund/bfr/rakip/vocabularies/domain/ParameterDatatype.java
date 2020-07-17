package de.bund.bfr.rakip.vocabularies.domain;

public class ParameterDatatype {

	private final int id;
	private final String name;
	private final String comment;
	private final String representedAs;
	
	public ParameterDatatype(int id, String name, String comment, String representedAs) {
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
