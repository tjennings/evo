package com.evo;

import com.evo.functions.Add;
import junit.framework.TestCase;

public class RandomMutationTest extends TestCase{
    private BasicIndividual bestIndiv;
    private Population population;
    private BasicFitnessEvaluator evaluator;
    private RandomTournamentSelector selector;
    private RandomPopulator populator;
    
    @Override
    public void setUp() {
        Symbol best = new Add().statement(new Constant(1), new Constant(1));
        bestIndiv = new BasicIndividual(best);

        
        evaluator = new BasicFitnessEvaluator() {{
            criteria().expects(2);
        }};
        
        population = new Population(evaluator) {{
            add(bestIndiv);
        }};

        selector = new RandomTournamentSelector(population);
        
        Grammar grammar = new Grammar();
        grammar.function(new Add());
        grammar.constant(2.0f);
        
        populator = new RandomPopulator(grammar);
    }
    
    public void testItShouldMutateTheBestProgramRandomly() {
        RandomMutation mutation = new RandomMutation(populator);
        Individual result = mutation.mutate(selector);
        
        assertFalse(result.sameAs(bestIndiv));
        assertNotNull(bestIndiv.result()); //Should be a valid program
    }  
}
