/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.evo;

import com.evo.functions.Add;
import junit.framework.TestCase;

/**
 *
 * @author tyler
 */
public class RandomTournamentSelectorTest extends TestCase {
    private Selector selector;
    private Individual bestIndiv;
    private Individual secondBestIndiv;
    private FitnessEvaluator evaluator;
    
    @Override
    public void setUp() {
        Symbol best = new Add().statement(new Constant(1), new Constant(1));
        bestIndiv = new BasicIndividual(best);
        Symbol secondBest = new Add().statement(new Constant(1), new Constant(1));
        secondBestIndiv = new BasicIndividual(secondBest);

        evaluator = new BasicFitnessEvaluator() {
            {
                criteria().expects(2);
            }
        };
        
        final Population population = new Population(evaluator) {
            {
                add(bestIndiv);
                add(secondBestIndiv);
            }
        };

        selector = new RandomTournamentSelector(population);
    }
    
    public void testEachNodeShouldHaveA50ProbabilityWhenErrorIsEqual() {
        assertFrequencyOfBest(50, bestIndiv, selector);
        assertFrequencyOfWorst(50, bestIndiv, selector);
    }
    
    private void assertFrequencyOfWorst(float expectedFrequency, Individual expectedIndiv, Selector selector) {
        float hit = 0;
        float sampleSize = 100000;
        for(int i = 0; i < sampleSize; i++ ) {
            if(expectedIndiv == selector.worst()) hit++;
        }
        assertEquals(expectedFrequency, (hit / sampleSize * 100), 0.5);
    }
    
    private void assertFrequencyOfBest(float expectedFrequency, Individual expectedIndiv, Selector selector) {
        float hit = 0;
        float sampleSize = 100000;
        for(int i = 0; i < sampleSize; i++ ) {
            if(expectedIndiv == selector.best()) hit++;
        }
        assertEquals(expectedFrequency, (hit / sampleSize * 100), 0.5);
    }
    
   
}
