/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.evo;

/**
 *
 * @author tyler
 */
class RandomTournamentSelector implements Selector{
    private Population population;
    private int samples = 100;
    
    public RandomTournamentSelector() {
        
    }
    
    public RandomTournamentSelector(Population population) {
        this.population = population;
    }

    public Individual best() {
        Individual best = population.randomIndividual();
        for(int i = 0; i < samples; i++) {
            Individual next = population.randomIndividual();
            if(next.betterThan(best)) {
                best = next;
            }
        }
        return best;
    }

    public Individual worst() {
        Individual worst = population.randomIndividual();
        for(int i = 0; i < samples; i++) {
            Individual next = population.randomIndividual();
            if(next.worseThan(worst)) {
                worst = next;
            }
        }
        return worst;
    }

    public Individual secondBest() {
        return best();
    }
    
    public void population(Population population) {
        this.population = population;
    }

    public void replaceWith(Individual child) {
        this.population.replace(worst(), child);
    }
    
    public void replaceWorstWith(Individual child) {
        
    }

}
