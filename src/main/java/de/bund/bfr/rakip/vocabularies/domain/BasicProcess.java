package de.bund.bfr.rakip.vocabularies.domain;

public class BasicProcess {

    private final int id;
    private final String name;
    private final ModelClass classCategory;

    public BasicProcess(int id, String name, ModelClass classCategory) {
        this.id = id;
        this.name = name;
        this.classCategory = classCategory;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ModelClass getClassCategory() {
        return classCategory;
    }
}
