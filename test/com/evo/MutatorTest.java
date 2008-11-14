package com.evo;

import junit.framework.TestCase;
import com.evo.functions.Add;


public class MutatorTest extends TestCase {
    private Selector selector;
    private Individual bestIndiv;
    private Individual worstIndiv;
    private Individual secondBestIndiv;
    private FitnessEvaluator evaluator;
    private Population population;


    public void setUp() {
        Symbol best = new Add().statement(new Constant(1), new Constant(1));
        bestIndiv = new BasicIndividual(best);
        Symbol secondBest = new Add().statement(new Constant(2), new Constant(1));
        secondBestIndiv = new BasicIndividual(secondBest);
        Symbol worst = new Add().statement(new Constant(2), new Constant(2));
        worstIndiv = new BasicIndividual(worst);

        

        evaluator = new BasicFitnessEvaluator() {
            {
                criteria().expects(2);
            }
        };
        
        population = new Population(evaluator) {
            {
                add(bestIndiv);
                add(secondBestIndiv);
                add(worstIndiv);
            }
        };

        selector = new RandomTournamentSelector(population);
    }
    
    public void testMutatorReplacesWorstIndiv() {
        Mutator mutator = new BasicMutator();
        mutator.setSelector(selector);
        mutator.mutationSet().add(100, new CrossoverMutation());

        mutator.mutate();

        assertNotNull(population.worst());
        assertNotSame(worstIndiv, population.worst());
        
    }
}
