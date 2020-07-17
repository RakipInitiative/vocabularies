package de.bund.bfr.rakip.vocabularies.domain;

public class AccreditationProcedure {
	
	private final int id;
	private final String mdstat;
	private final String name;
	
	public AccreditationProcedure(int id, String mdstat, String name) {
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
