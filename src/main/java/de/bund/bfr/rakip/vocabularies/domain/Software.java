package de.bund.bfr.rakip.vocabularies.domain;

public class Software {

    private final int id;
    private final String name;

    public Software(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
