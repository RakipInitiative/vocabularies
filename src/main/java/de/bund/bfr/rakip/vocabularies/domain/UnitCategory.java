package de.bund.bfr.rakip.vocabularies.domain;

public class UnitCategory {

    private final int id;
    private final String name;

    public UnitCategory(int id, String name) {
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
