package com.evo;

public class Evolver {

    public static Evolver create(final Grammar grammar, final FitnessEvaluator evaluator) {
        Evolver evo = new Evolver();        
                
        //program generator
        Populator populator = new RandomPopulator(grammar);
        final Population initial = new Population(populator.newPopulation(10000), evaluator);
        
        //selector
        Selector selector = new RandomTournamentSelector() {{
            population(initial);
        }};
        
        //mutationset
        Mutator mutator = new BasicMutator();
        mutator.setSelector(selector);
        mutator.mutationSet().add(60, new CrossoverMutation());
        mutator.mutationSet().add(40, new RandomMutation(populator));
        
        evo.setMutator(mutator);
        evo.setPopulator(populator);
        evo.setSelector(selector);
        evo.setPopulation(initial);
        
        return evo;
    }
    
    private Populator populator;    
    private Mutator mutator;
    private Selector selector;
    
    private int generationsToRun = 0;
    private float solutionThreshold = 0.0f;
    private Individual curr_best;
    private Population population;

    public Individual best() {
        return selector.best();
    }
    

    public void generationsToRun(int i) {
        this.generationsToRun = i;
    }

    public Individual run() {                
        for(int i = 0; i < generationsToRun; i++) {
            for(int p = 0; p < population.size(); p++) {
                mutator.mutate();   
            
            Individual best = population.best();

                if(best.error() <= solutionThreshold) {
                    return best();
                }

                if(curr_best != best) {
                    curr_best = best;
                    System.out.println(curr_best.error());
                    //System.out.println("\n\n" + curr_best);
                }
            }
            System.out.println("Generation Complete");
        }
        
        return null;
    }

    public void setMutator(Mutator mutator) {
        this.mutator = mutator;
    }

    public void setPopulator(Populator populator) {
        this.populator = populator;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public void solutionThreshold(float solutionThreshold) {
       this.solutionThreshold = solutionThreshold;
    }

    private void setPopulation(Population initial) {
       this.population = initial;
    }
}
