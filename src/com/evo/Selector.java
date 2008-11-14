package com.evo;

public interface Selector {
    Individual best();
    Individual worst();
    Individual secondBest();

    void replaceWorstWith(Individual child);
    
    void replaceWith(Individual child);
}
