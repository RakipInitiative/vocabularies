package de.bund.bfr.rakip.vocabularies.domain;

public class LanguageWrittenIn {

    private final int id;
    private final String name;

    public LanguageWrittenIn(int id, String name) {
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
