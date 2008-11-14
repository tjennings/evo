package com.evo;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class RandomPopulator implements Populator {
    Random random = new Random();
    private Grammar grammar;

    private int maxDepth = 5;

    public RandomPopulator(Grammar grammar) {
        this.grammar = grammar;
    }

    public Individual newIndividual() {
        return IndividualFactory.create(grow());
    }

    public Symbol grow() {
        return grow(0, maxDepth);
    }


    private Symbol grow(int pos, int depthRemaining) {
        boolean useTerminal = random.nextBoolean();

        if(useTerminal || depthRemaining == 0) {
            return randomTerminal();
        } else {
            return randomFunction(grow(pos + 1, depthRemaining - 1), grow(pos + 1, depthRemaining - 1));
      }
    }


    public void setGrammar(Grammar grammar) {
        this.grammar = grammar;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }


    public List<Individual> newPopulation(int popSize) {
        List<Individual> population = new ArrayList<Individual>();
        for(int i = 0; i < popSize; i++) {
            population.add(IndividualFactory.create(grow()));
        }
        return population;
    }

    private Symbol randomFunction(Symbol left, Symbol right) {
        return grammar.randomFunction().statement(left, right);
    }

    private Symbol randomTerminal() {
        return grammar.randomTerminal();
    }
}