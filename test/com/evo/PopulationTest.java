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
public class PopulationTest extends TestCase {
    private BasicIndividual bestIndiv;
    private BasicIndividual secondBestIndiv;
    private BasicFitnessEvaluator evaluator;
    private Population population;
    private BasicIndividual worstIndiv;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        Symbol best = new Add().statement(new Constant(1), new Constant(1));
        bestIndiv = new BasicIndividual(best);
        Symbol secondBest = new Add().statement(new Constant(5), new Constant(5));
        secondBestIndiv = new BasicIndividual(secondBest);
        Symbol thirdBest = new Add().statement(new Constant(10), new Constant(20));
        worstIndiv = new BasicIndividual(thirdBest);

        evaluator = new BasicFitnessEvaluator() {{
            criteria().expects(2);
        }};
        
        population = new Population(evaluator) {{
            add(bestIndiv);
            add(secondBestIndiv);
            add(worstIndiv);
        }};
    }
    
    public void testReturnBestFromPopulation() {
        assertEquals(bestIndiv, population.best());
    }
        
    public void testReturnSecondBest() {
        assertEquals(secondBestIndiv, population.secondBest());
    }    
    
    public void testReturnWorstFromPopulation() {
        assertEquals(worstIndiv, population.worst());
    }
    
    public void testAddReCalculatesIndividuals() {
        Symbol newProg = new Add().statement(new Constant(2), new Constant(1));
        BasicIndividual newIndiv = new BasicIndividual(newProg);
        
        population.add(newIndiv);
        
        assertEquals(bestIndiv, population.best());
        assertEquals(newIndiv, population.secondBest());
        assertEquals(worstIndiv, population.worst());
    }
    
    public void testRepaceReCalculatesIndividuals() {
        Symbol newProg = new Add().statement(new Constant(2), new Constant(1));
        BasicIndividual newIndiv = new BasicIndividual(newProg);
        
        population.replace(bestIndiv, newIndiv);
        
        assertEquals(newIndiv, population.best());
        assertEquals(secondBestIndiv, population.secondBest());
        assertEquals(worstIndiv, population.worst());
        
        population.replace(worstIndiv, bestIndiv);
        
        assertEquals(bestIndiv, population.best());
        assertEquals(newIndiv, population.secondBest());
        assertEquals(secondBestIndiv, population.worst());
    }

}
