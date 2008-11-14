package com.evo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tyler
 * Date: Aug 1, 2008
 * Time: 7:16:41 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Populator {
    Individual newIndividual();

    void setGrammar(Grammar grammar);

    void setMaxDepth(int maxDepth);

    List<Individual> newPopulation(int popSize);
    
    Symbol grow();
}
