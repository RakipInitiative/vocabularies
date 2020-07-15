package de.bund.bfr.rakip.vocabularies.data;

import java.util.Optional;

public interface BasicRepository<T> {

    Optional<T> getById(int id);

    T[] getAll();
    
    String[] getAllNames();
}
