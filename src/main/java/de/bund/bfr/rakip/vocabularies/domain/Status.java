package de.bund.bfr.rakip.vocabularies.domain;

public class Status {

    private final int id;
    private final String name;
    private final String comment;

    public Status(int id, String name, String comment) {
        this.id = id;
        this.name = name;
        this.comment = comment;
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
}
