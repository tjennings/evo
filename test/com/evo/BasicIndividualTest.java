package com.evo;

import junit.framework.TestCase;

public class BasicIndividualTest extends TestCase{
    public void testIndividualLength() {
        Symbol root = new Node(null, new Constant(1), new Constant(1));
        Individual indiv = new BasicIndividual(root);
        
        assertEquals(3, indiv.length());
        
        Node left = new Node(null, new Constant(1), new Constant(1));
        Node right = new Node(null, new Constant(1), new Variable("X"));
        Symbol larger = new Node(null, left, right);
        assertEquals(7, larger.length());
    }
    
    public void testSameAsWithIdenticalIndivs() {
        Symbol root = new Node(null, new Constant(1), new Constant(1));
        Individual one = new BasicIndividual(root);
        Individual two = new BasicIndividual(root);
        assertTrue(one.sameAs(two));
    }
    
    public void testSameAsWithDifferentIndivs() {
        Symbol root = new Node(null, new Constant(1), new Constant(1));
        Symbol root2 = new Node(null, new Constant(1), new Constant(2));
        
        Individual one = new BasicIndividual(root);
        Individual two = new BasicIndividual(root2);
        assertFalse(one.sameAs(two));
    }
    
    public void testCloneIndividual() {
        Symbol root = new Node(null, new Constant(1), new Constant(1));
        Individual indiv = new BasicIndividual(root);
        Individual clone = indiv.clone();
        
        assertTrue(clone.sameAs(indiv));
    }
}