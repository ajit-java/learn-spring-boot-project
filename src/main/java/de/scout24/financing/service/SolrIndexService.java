package de.scout24.financing.service;

import java.io.Serializable;

public interface SolrIndexService<T, V extends Serializable> {

    void addToIndex(T domain);
    
    void removeFromIndex(V id);
}
