package com.evo;

/**
 * This selector only mutates the best individual with the second best individual.
 * @author tyler
 */
public class ElitistTournamentSelector implements Selector {
    private Population population;
    
    public ElitistTournamentSelector() {
        
    }

    public ElitistTournamentSelector(Population population) {
        this.population = population;
    }

    public void population(Population population) {
        this.population = population;
    };

    public void replaceWorstWith(Individual child) {
        this.population.replaceWorstWith(child);
    }
    
    public void replaceWith(Individual child) {
        this.population.replace(worst(), child);
    }

    public Individual best() {
        return population.best();
    }

    public Individual secondBest() {
        return population.secondBest();
    }

    public Individual worst() {
        return population.worst();
    }
}
