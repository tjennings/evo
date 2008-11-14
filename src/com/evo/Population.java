package com.evo;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Population {
    private static final Random random = new Random();
    private List<Individual> population = new ArrayList<Individual>();
    private FitnessEvaluator evaluator = null;

    public Population(FitnessEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public Population(List<Individual> population, FitnessEvaluator evaluator) {
        this.evaluator = evaluator;
        this.population.addAll(population);
        orderPopulation(population);
    }

    public void add(Individual indiv) {
        orderIndividual(indiv);
    }

    public Individual best() {
        return population.get(0);
    }

    public Individual secondBest() {
        return population.get(1);
    }

    public Individual worst() {
        return population.get(population.size() - 1);
    }


    public Individual get(int i) {
        return population.get(i);
    }

    public int size() {
        return population.size();
    }

    public void replaceWorstWith(Individual child) {
        replace(worst(), child);
    }

    public void clear() {
        population.clear();
    }

    public Individual randomIndividual() {
        return population.get(random.nextInt(population.size()));
    }
    
    public void replace(Individual target, Individual replacement) {
        population.remove(target);
        add(replacement);
    }
    
    private void orderIndividual(Individual indiv) {
        orderPopulation(Collections.singletonList(indiv));
    }
    
    private void sortPopulation() {
//            Collections.sort(population, new Comparator() {
//
//            public int compare(Object o, Object o1) {
//                Individual left = (Individual) o;
//                Individual right = (Individual) o1;
//
//                if(left.error() == right.error())
//                    return 0;
//                else if(left.error() < right.error())
//                    return -1;
//                else
//                    return 1;
//            }
//        });
    }

    private void orderPopulation(List<Individual> newPopulation) {
        for(Individual addition : newPopulation) {
            evaluator.evaluate(addition);
            
            if(population.isEmpty()) {
                population.add(addition);
                continue;
            }

            int addIndex = population.size();
            for(Individual existing : population) {
                if(existing.error() > addition.error()) {
                    addIndex = population.indexOf(existing);
                    break;
                }
            }
            population.add(addIndex, addition);
        }
    }
}
